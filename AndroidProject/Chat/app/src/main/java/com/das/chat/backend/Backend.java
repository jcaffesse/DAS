package com.das.chat.backend;

import android.content.SharedPreferences;
import android.util.Log;

import com.das.chat.application.ChatApplication;
import com.das.chat.dao.ChatInvitation;
import com.das.chat.dao.ChatMessage;
import com.das.chat.dao.ChatRoom;
import com.das.chat.dao.ChatUser;
import com.das.chat.wsmodelmap.AddRoomRequest;
import com.das.chat.wsmodelmap.EnterChatRoomGetMessagesResponse;
import com.das.chat.wsmodelmap.EnterChatRoomRequest;
import com.das.chat.wsmodelmap.EnterChatRoomGetUsersResponse;
import com.das.chat.wsmodelmap.GetInvitationsRequest;
import com.das.chat.wsmodelmap.GetInvitationsResponse;
import com.das.chat.wsmodelmap.ListRoomsResponse;
import com.das.chat.wsmodelmap.ListUsersResponse;
import com.das.chat.wsmodelmap.LoginRequest;
import com.das.chat.wsmodelmap.LoginResponse;
import com.das.chat.wsmodelmap.RegisterRequest;
import com.das.chat.wsmodelmap.SendMessageRequest;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * Created by Pablo Alday on 10/02/2015.
 */
public class Backend
{
    private static final String WS_BASE_URL = "http://10.0.2.2:8080";
    private static final String WS_ROOMS_URL = "/salas";
    private static final String WS_LOGIN_URL = "/login";
    private static final String WS_USERS_URL = "/usuarios";
    private static final String WS_USERS_ROOMS_URL = "/usuarios-salas";
    private static final String WS_MESSAGES_URL = "/mensajes";
    private static final String WS_INVITATIONS_URL = "/invitaciones";

    private static Backend instance = new Backend();
    private ArrayList<ChatUser> users;
    private ArrayList<ChatRoom> rooms;
    private ChatUser session;

    private SharedPreferences updateTime;

    private static synchronized void initInstance()
    {
        if (instance == null)
        {
            instance = new Backend();
        }
    }

    public static Backend getInstance()
    {
        initInstance();
        return instance;
    }

    private Backend()
    {
        updateTime = ChatApplication.getAppContext().getSharedPreferences("com.das.chat.last_update_time", 0);
    }

    public ChatUser getUserById(String userId) {
        for (ChatUser user : users) {
            if(user.getUserId().compareTo(userId) == 0) {
                return user;
            }
        }
        return null;
    }

    public void setLastInvitationUpdateTime() {
        updateTime.edit().putString("com.das.chat.last_update_time.invites", Long.toString(new Date().getTime())).apply();
    }

    public void setLastGeneralUpdateTime() {
        updateTime.edit().putString("com.das.chat.last_update_time.general", Long.toString(new Date().getTime())).apply();
    }

    public void setLastRoomUpdateTime(String chatRoomId) {
        updateTime.edit().putString("com.das.chat.last_update_time.room_" + chatRoomId, Long.toString(new Date().getTime())).apply();
    }

    public String getLastInvitationUpdateTime() {
        return updateTime.getString("com.das.chat.last_update_time.invites", "0");
    }

    public String getLastGeneralUpdateTime() {
        return updateTime.getString("com.das.chat.last_update_time.general", "0");
    }

    public String getLastRoomUpdateTime(String chatRoomId) {
        return updateTime.getString("com.das.chat.last_update_time.room_" + chatRoomId, "0");
    }

    public ArrayList<ChatUser> getUsers() {
        return users;
    }

    public ArrayList<ChatRoom> getRooms() {
        return rooms;
    }

    public void updateRoomAlert(String roomId, boolean alert) {
        for (ChatRoom room : rooms) {
            if(room.getIdSala().compareTo(roomId) == 0) {
                room.setAlertaSala(alert);
                break;
            }
        }
    }

    public ChatUser getSession() {
        return session;
    }

    public void login(LoginRequest req, final OnWSResponseListener<Boolean> responseListener)
    {
        ChatWSTask task = new ChatWSTask();
        WSParams params = new WSParams();

        HttpPost post = new HttpPost(String.format("%s%s", WS_BASE_URL, WS_LOGIN_URL));

        params.setRequestWithBody(post, req.getForm());

        task.setResponseListener(new OnWSResponseListener<String>()
        {
            @Override
            public void onWSResponse(final String response, final long errorCode, final String errorMsg) {
                if (errorMsg == null)
                {
                    session = LoginResponse.initWithResponse(response);
                    responseListener.onWSResponse(true, errorCode, null);
                }
                else
                {
                    responseListener.onWSResponse(false, errorCode, errorMsg);
                }
            }
        });
        task.execute(params);
    }


    public void register(RegisterRequest req, final OnWSResponseListener<Boolean> responseListener)
    {
        ChatWSTask task = new ChatWSTask();
        WSParams params = new WSParams();

        HttpPost post = new HttpPost(String.format("%s%s", WS_BASE_URL, WS_USERS_URL));

        params.setRequestWithBody(post, req.getForm());

        task.setResponseListener(new OnWSResponseListener<String>()
        {
            @Override
            public void onWSResponse(final String response, final long errorCode, final String errorMsg) {
                if (errorMsg == null)
                {
                    responseListener.onWSResponse(true, errorCode, null);
                }
                else
                {
                    responseListener.onWSResponse(false, errorCode, errorMsg);
                }
            }
        });
        task.execute(params);
    }

    public void enterChatRoom(EnterChatRoomRequest req, final OnWSResponseListener<Boolean> responseListener)
    {
        ChatWSTask task = new ChatWSTask();
        WSParams params = new WSParams();

        req.setIdUsuario(session.getUserId());
        req.setEstado("1");
        HttpPut put = new HttpPut(String.format("%s%s", WS_BASE_URL, WS_USERS_ROOMS_URL));
        Log.d("REQUEST", String.format("%s%s", WS_BASE_URL, WS_USERS_ROOMS_URL));

        params.setRequestWithBody(put, req.getForm());
        params.addTokenHeader(session.getSessionToken());
        Log.d("REQUEST", req.getForm());

        task.setResponseListener(new OnWSResponseListener<String>() {
            @Override
            public void onWSResponse(final String response, final long errorCode, final String errorMsg) {
                if (errorMsg == null) {
                    responseListener.onWSResponse(true, errorCode, null);
                } else {
                    responseListener.onWSResponse(false, errorCode, errorMsg);
                }
            }
        });
        task.execute(params);
    }

    public void getChatRoomUsers(EnterChatRoomRequest req, final OnWSResponseListener<ArrayList<ChatUser>> responseListener)
    {
        ChatWSTask task = new ChatWSTask();
        WSParams params = new WSParams();

        HttpGet get = new HttpGet(String.format("%s%s/sala/%s", WS_BASE_URL, WS_USERS_ROOMS_URL, req.getIdSala()));
        Log.d("REQUEST", String.format("%s%s/sala/%s", WS_BASE_URL, WS_USERS_ROOMS_URL, req.getIdSala()));

        params.setRequest(get);
        params.addTokenHeader(session.getSessionToken());

        task.setResponseListener(new OnWSResponseListener<String>() {
            @Override
            public void onWSResponse(final String response, final long errorCode, final String errorMsg) {
                if (errorMsg == null) {
                    ArrayList<ChatUser> users = EnterChatRoomGetUsersResponse.initWithResponse(response);
                    responseListener.onWSResponse(users, errorCode, null);
                } else {
                    responseListener.onWSResponse(null, errorCode, errorMsg);
                }
            }
        });
        task.execute(params);
    }

    public void getChatRoomMessages(final EnterChatRoomRequest req, String date, final OnWSResponseListener<ArrayList<ChatMessage>> responseListener)
    {
        ChatWSTask task = new ChatWSTask();
        WSParams params = new WSParams();

        HttpGet get = new HttpGet(String.format("%s%s/sala/%s?ultima_act=%s", WS_BASE_URL, WS_MESSAGES_URL, req.getIdSala(), date));
        Log.d("REQUEST", String.format("%s%s/sala/%s?ultima_act=%s", WS_BASE_URL, WS_MESSAGES_URL, req.getIdSala(), date));
        params.setRequest(get);
        params.addTokenHeader(session.getSessionToken());

        task.setResponseListener(new OnWSResponseListener<String>() {
            @Override
            public void onWSResponse(final String response, final long errorCode, final String errorMsg) {
                if (errorMsg == null) {
                    ArrayList<ChatMessage> messages = EnterChatRoomGetMessagesResponse.initWithResponse(response);
                    if(messages.size() > 0) {
                        setLastRoomUpdateTime(req.getIdSala());
                    }
                    responseListener.onWSResponse(messages, errorCode, null);
                } else {
                    responseListener.onWSResponse(null, errorCode, errorMsg);
                }
            }
        });
        task.execute(params);
    }

    public void getMessages(String date, final OnWSResponseListener<ArrayList<ChatMessage>> responseListener)
    {
        ChatWSTask task = new ChatWSTask();
        WSParams params = new WSParams();

        HttpGet get = new HttpGet(String.format("%s%s/mensajes/%s?ultima_act=%s", WS_BASE_URL, WS_USERS_ROOMS_URL, session.getUserId(), date));
        Log.d("REQUEST", String.format("%s%s/mensajes/%s?ultima_act=%s", WS_BASE_URL, WS_USERS_ROOMS_URL, session.getUserId(), date));
        params.setRequest(get);
        params.addTokenHeader(session.getSessionToken());

        task.setResponseListener(new OnWSResponseListener<String>() {
            @Override
            public void onWSResponse(final String response, final long errorCode, final String errorMsg) {
                if (errorMsg == null) {
                    ArrayList<ChatMessage> messages = EnterChatRoomGetMessagesResponse.initWithResponse(response);
                    responseListener.onWSResponse(messages, errorCode, null);
                    setLastGeneralUpdateTime();
                    for (ChatMessage message : messages) {
                        updateRoomAlert(message.getIdChatRoom(), true);
                    }
                } else {
                    responseListener.onWSResponse(null, errorCode, errorMsg);
                }
            }
        });
        task.execute(params);
    }

    public void getRoomList(final OnWSResponseListener<ArrayList<ChatRoom>> responseListener)
    {
        ChatWSTask task = new ChatWSTask();
        WSParams params = new WSParams();

        HttpGet get = new HttpGet(String.format("%s%s", WS_BASE_URL, WS_ROOMS_URL));

        params.setRequest(get);
        params.addTokenHeader(session.getSessionToken());

        task.setResponseListener(new OnWSResponseListener<String>()
        {
            @Override
            public void onWSResponse(final String response, final long errorCode, final String errorMsg) {
                if (errorMsg == null)
                {
                    rooms = ListRoomsResponse.initWithResponse(response);
                    responseListener.onWSResponse(rooms, errorCode, null);
                }
                else
                {
                    responseListener.onWSResponse(null, errorCode, errorMsg);
                }
            }
        });
        task.execute(params);
    }

    public void getInvitationList(String date, final OnWSResponseListener<ArrayList<ChatInvitation>> responseListener)
    {
        ChatWSTask task = new ChatWSTask();
        WSParams params = new WSParams();

        HttpGet get = new HttpGet(String.format("%s%s/%s?ultima_act=%s", WS_BASE_URL, WS_INVITATIONS_URL, session.getUserId(), date));
        Log.d("REQUEST", String.format("%s%s/%s?ultima_act=%s", WS_BASE_URL, WS_INVITATIONS_URL, session.getUserId(), date));
        params.setRequest(get);
        params.addTokenHeader(session.getSessionToken());

        task.setResponseListener(new OnWSResponseListener<String>()
        {
            @Override
            public void onWSResponse(final String response, final long errorCode, final String errorMsg) {
                if (errorMsg == null)
                {
                    ArrayList<ChatInvitation> rooms = GetInvitationsResponse.initWithResponse(response);
                    responseListener.onWSResponse(rooms, errorCode, null);
                    setLastInvitationUpdateTime();
                }
                else
                {
                    responseListener.onWSResponse(null, errorCode, errorMsg);
                }
            }
        });
        task.execute(params);
    }

    public void getUserList(final OnWSResponseListener<ArrayList<ChatUser>> responseListener)
    {
        ChatWSTask task = new ChatWSTask();
        WSParams params = new WSParams();

        HttpGet get = new HttpGet(String.format("%s%s", WS_BASE_URL, WS_USERS_URL));

        params.setRequest(get);
        params.addTokenHeader(session.getSessionToken());

        task.setResponseListener(new OnWSResponseListener<String>()
        {
            @Override
            public void onWSResponse(final String response, final long errorCode, final String errorMsg) {
                if (errorMsg == null)
                {
                    users = ListUsersResponse.initWithResponse(response);
                    responseListener.onWSResponse(users, errorCode, null);
                }
                else
                {
                    responseListener.onWSResponse(null, errorCode, errorMsg);
                }
            }
        });
        task.execute(params);
    }

    public void sendMessage(final SendMessageRequest req, final OnWSResponseListener<Boolean> responseListener)
    {
        ChatWSTask task = new ChatWSTask();
        WSParams params = new WSParams();

        HttpPost post = new HttpPost(String.format("%s%s", WS_BASE_URL, WS_MESSAGES_URL));

        params.setRequestWithBody(post, req.getForm());
        params.addTokenHeader(session.getSessionToken());
        Log.d("REQUEST", req.getForm());
        task.setResponseListener(new OnWSResponseListener<String>()
        {
            @Override
            public void onWSResponse(final String response, final long errorCode, final String errorMsg)
            {
                if (errorMsg == null)
                {
                    responseListener.onWSResponse(true, errorCode, null);
                }
                else
                {
                    responseListener.onWSResponse(false, errorCode, errorMsg);
                }
            }
        });
        task.execute(params);
    }


    public void addRoom(AddRoomRequest req, final OnWSResponseListener<Boolean> responseListener)
    {
        ChatWSTask task = new ChatWSTask();
        WSParams params = new WSParams();

        HttpPost post = new HttpPost(String.format("%s%s", WS_BASE_URL, WS_ROOMS_URL));

        params.setRequestWithBody(post, req.getForm());


        task.setResponseListener(new OnWSResponseListener<String>()
        {
            @Override
            public void onWSResponse(final String response, final long errorCode, final String errorMsg)
            {
                if (errorMsg == null)
                {
                    responseListener.onWSResponse(true, errorCode, null);
                }
                else
                {
                    responseListener.onWSResponse(false, errorCode, errorMsg);
                }
            }
        });
        task.execute(params);
    }
}
