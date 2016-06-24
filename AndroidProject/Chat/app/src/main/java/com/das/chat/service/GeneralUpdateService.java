package com.das.chat.service;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.das.chat.backend.Backend;
import com.das.chat.backend.OnWSResponseListener;
import com.das.chat.dao.ChatInvitation;
import com.das.chat.dao.ChatMessage;
import com.das.chat.dao.ChatRoom;
import com.das.chat.wsmodelmap.EnterChatRoomRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class GeneralUpdateService extends Service {

    private final IBinder mBinder = new LocalBinder();
    private Timer generalTimer = null;
    private Timer invitesTimer = null;
    private Timer chatRoomTimer = null;
    Callbacks callbackClient;
    private Notification notification;
    private NotificationManager notificationManager;
    private DateFormat format;
    private ChatRoom chatRoomUpdating;

    public GeneralUpdateService() {
    }

    @Override
    public void onCreate()
    {
        super.onCreate();

        Log.d("SERVICE", "-------------- CREATE --------------");

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent)
    {
        Log.d("SERVICE", "----------- TASK REMOVED -----------");
        super.onTaskRemoved(rootIntent);

        onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("SERVICE", "--------- SERVICE STARTED ---------");
        startInvitationsTimer();
        startGeneralMessagesTimer();
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        stopSelf();

        if (generalTimer != null) {
            generalTimer.cancel();
            generalTimer = null;
        }

        if (invitesTimer != null) {
            invitesTimer.cancel();
            invitesTimer = null;
        }

        if (chatRoomTimer != null) {
            chatRoomTimer.cancel();
            chatRoomTimer = null;
        }

        stopForeground(true);
        Log.d("SERVICE", "------------- DESTROY -------------");
        super.onDestroy();
    }

    public class LocalBinder extends Binder {
        public GeneralUpdateService getService() {
            Log.d("SERVICE", "----------- BINDED ------------");
            return GeneralUpdateService.this;
        }
    }


    public void startInvitationsTimer() {
        invitesTimer = new Timer();
        invitesTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Backend.getInstance().getInvitationList(Backend.getInstance().getLastInvitationUpdateTime(), new OnWSResponseListener<ArrayList<ChatInvitation>>() {
                    @Override
                    public void onWSResponse(ArrayList<ChatInvitation> response, long errorCode, String errorMsg) {
                        callbackClient.updateInvitations();
                        Backend.getInstance().setLastInvitationUpdateTime();
                        Log.d("SERVICE", "----------- UPDATING INVITES -----------");
                    }
                });
            }
        }, 0, 20000L);
    }

    public void startGeneralMessagesTimer() {
        generalTimer = new Timer();
        generalTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Backend.getInstance().getMessages(Backend.getInstance().getLastGeneralUpdateTime(), new OnWSResponseListener<ArrayList<ChatMessage>>() {
                    @Override
                    public void onWSResponse(ArrayList<ChatMessage> response, long errorCode, String errorMsg) {
                        if (errorMsg == null) {
                            callbackClient.updateMessages();
                            Backend.getInstance().setLastGeneralUpdateTime();
                            Log.d("SERVICE", "----------- UPDATING MESSAGES -----------");
                        }
                    }
                });
            }
        }, 0, 5000L);
    }

    public void startChatRoomTimer() {
        chatRoomTimer = new Timer();
        chatRoomTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                EnterChatRoomRequest req = new EnterChatRoomRequest();
                req.setIdSala(String.valueOf(chatRoomUpdating.getIdSala()));
                Backend.getInstance().getChatRoomMessages(req, Backend.getInstance().getLastRoomUpdateTime(), new OnWSResponseListener<ArrayList<ChatMessage>>() {
                    @Override
                    public void onWSResponse(ArrayList<ChatMessage> response, long errorCode, String errorMsg) {
                        if (errorMsg == null) {
                            callbackClient.updateMessagesForChatRoom(response);
                            Backend.getInstance().setLastRoomUpdateTime();
                            Log.d("SERVICE", "----------- UPDATING CHAT ROOM " + chatRoomUpdating.getNombreSala() + "-----------");
                        }
                    }
                });
            }
        }, 0, 5000L);
    }

    public void stopChatRoomTimer() {
        chatRoomTimer.cancel();
        chatRoomTimer = null;
    }

    public interface Callbacks {
        void updateInvitations();
        void updateMessages();
        void updateMessagesForChatRoom(ArrayList<ChatMessage> messages);
    }

    //Here Activity register to the service as Callbacks client
    public void registerClient(Activity activity){
        this.callbackClient = (Callbacks)activity;
    }

    //Here Activity register to the service as Callbacks client
    public void registerChatClient(Activity activity, ChatRoom room){
        this.callbackClient = (Callbacks)activity;
        this.chatRoomUpdating = room;
    }
}