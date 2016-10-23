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
    GeneralCallbacks generalCallbackClient;
    ChatRoomCallbacks chatRoomCallbackClient;
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
                        if(response.size() > 0) {
                            generalCallbackClient.updateInvitations(response);
                        }
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
                            if(response.size() > 0) {
                                generalCallbackClient.updateMessages();
                                Backend.getInstance().setLastGeneralUpdateTime();
                            }

                            Log.d("SERVICE", "----------- UPDATING ALL MESSAGES -----------");
                        }
                    }
                });
            }
        }, 0, 10000L);
    }

    public void startChatRoomTimer() {
        chatRoomTimer = new Timer();
        chatRoomTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                EnterChatRoomRequest req = new EnterChatRoomRequest();
                req.setIdSala(String.valueOf(chatRoomUpdating.getIdSala()));
                Backend.getInstance().getChatRoomMessages(req, Backend.getInstance().getLastRoomUpdateTime(req.getIdSala()), new OnWSResponseListener<ArrayList<ChatMessage>>() {
                    @Override
                    public void onWSResponse(ArrayList<ChatMessage> response, long errorCode, String errorMsg) {
                        if (errorMsg == null) {
                            chatRoomCallbackClient.updateMessagesForChatRoom(response);
                            Log.d("SERVICE", "----------- UPDATING CHAT ROOM " + chatRoomUpdating.getNombreSala() + "-----------");
                        }
                    }
                });
            }
        }, 0, 10000L);
    }

    public void startGeneralTimers() {
        startInvitationsTimer();
        startGeneralMessagesTimer();
    }

    public void stopChatRoomTimer() {
        chatRoomTimer.cancel();
        chatRoomTimer = null;
    }

    public void stopGeneralUpdateTimer() {
        invitesTimer.cancel();
        invitesTimer = null;
        generalTimer.cancel();
        generalTimer = null;
    }

    public interface GeneralCallbacks {
        void updateInvitations(ArrayList<ChatInvitation> invites);
        void updateMessages();
    }

    public interface ChatRoomCallbacks {
        void updateMessagesForChatRoom(ArrayList<ChatMessage> messages);
    }

    //Here Activity register to the service as Callbacks client
    public void registerGeneralUpdateClient(Activity activity) {
        this.generalCallbackClient = (GeneralCallbacks)activity;
    }

    //Here Activity register to the service as Callbacks client
    public void registerChatRoomClient(Activity activity, ChatRoom room){
        this.chatRoomCallbackClient = (ChatRoomCallbacks)activity;
        this.chatRoomUpdating = room;
    }


}