package com.das.chat.backend;

import android.content.SharedPreferences;
import android.util.Log;

import com.das.chat.application.ChatApplication;
import com.das.chat.dao.ChatInvitation;
import com.das.chat.dao.ChatMessage;
import com.das.chat.dao.ChatRoom;
import com.das.chat.dao.ChatUpdate;
import com.das.chat.dao.ChatUser;
import com.das.chat.wsmodelmap.AddRoomRequest;
import com.das.chat.wsmodelmap.EnterChatRoomGetMessagesResponse;
import com.das.chat.wsmodelmap.EnterChatRoomRequest;
import com.das.chat.wsmodelmap.EnterChatRoomGetUsersResponse;
import com.das.chat.wsmodelmap.GetInvitationsResponse;
import com.das.chat.wsmodelmap.GetUpdatesRequest;
import com.das.chat.wsmodelmap.GetUpdatesResponse;
import com.das.chat.wsmodelmap.ListRoomsResponse;
import com.das.chat.wsmodelmap.ListUsersResponse;
import com.das.chat.wsmodelmap.LoginRequest;
import com.das.chat.wsmodelmap.LoginResponse;
import com.das.chat.wsmodelmap.RegisterRequest;
import com.das.chat.wsmodelmap.SendInvitationRequest;
import com.das.chat.wsmodelmap.SendInvitationResponse;
import com.das.chat.wsmodelmap.SendMessageRequest;
import com.das.chat.wsmodelmap.UpdateInvitationRequest;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Backend
{
    //private static final String WS_BASE_URL = "http://10.0.2.2:8080";
    private static final String WS_BASE_URL = "http://25.136.78.82:8080";
    private static final String WS_ROOMS_USERS_URL = "/salas/usuario";
    private static final String WS_ROOMS_URL = "/salas";
    private static final String WS_LOGIN_URL = "/login";
    private static final String WS_LOGOUT_URL = "/logout";
    private static final String WS_USERS_URL = "/usuarios";
    private static final String WS_USERS_ROOMS_URL = "/usuarios-salas";
    private static final String WS_MESSAGES_URL = "/mensajes";
    private static final String WS_INVITATIONS_URL = "/invitaciones";
    private static final String WS_UPDATES_URL = "/actualizaciones";

    private static Backend instance = new Backend();
    private ArrayList<ChatUser> users;
    private ArrayList<ChatRoom> rooms;
    private HashMap<String, String> myRooms;
    private ChatUser session;

    private SharedPreferences updateTime;

    private static synchronized void initInstance()
    {
        if (instance == null)
        {
            instance = new Backend();
        }
    }

    public void setEnterRoomMessageId(String roomId, String messageId) {
        if(!myRooms.containsKey(roomId)) {
            myRooms.put(roomId, messageId);
            setLastRoomUpdateMessageId(roomId, messageId);
        }
    }

    public void removeEnterRoomMessageId(String roomId) {
        myRooms.remove(roomId);
        deleteLastRoomUpdateMessageId(roomId);
    }

    public void removeAllEnterRoomMessageId() {
        Iterator it = myRooms.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            myRooms.remove(pair.getKey());
            deleteLastRoomUpdateMessageId(pair.getKey().toString());
            System.out.println(pair.getKey() + " = " + pair.getValue());
        }
    }

    public String getEnterRoomMessageId(String roomId) {
        if (myRooms.containsKey(roomId)) {
            return myRooms.get(roomId);
        } else {
            Backend.getInstance().setLastGeneralUpdateTime(roomId);
            return "-1";
        }
    }

    public ChatRoom getChatRoomById (String roomId) {
        for (ChatRoom room : rooms) {
            if(room.getIdSala().compareTo(roomId) == 0)
            {
                return room;
            }
        }
        return null;
    }

    public static Backend getInstance()
    {
        initInstance();
        return instance;
    }

    private Backend()
    {
        updateTime = ChatApplication.getAppContext().getSharedPreferences("com.das.chat.last_update_time", 0);
        myRooms = new HashMap<>();
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
        updateTime.edit().putString("com.das.chat.last_update_time.invites", Long.toString(new Date().getTime()+300)).apply();
    }

    public void setLastGeneralUpdateTime(String chatRoomId) {
        updateTime.edit().putString("com.das.chat.last_update_time.general_" + chatRoomId, Long.toString(new Date().getTime()+300)).apply();
    }

    public void setLastRoomUpdateMessageId(String chatRoomId, String messageId) {
        updateTime.edit().putString("com.das.chat.last_update_time.room_" + chatRoomId, messageId).apply();
        setEnterRoomMessageId(chatRoomId, messageId);
    }

    public void deleteLastRoomUpdateMessageId(String chatRoomId) {
        updateTime.edit().remove("com.das.chat.last_update_time.room_" + chatRoomId).apply();
    }

    public String getLastInvitationUpdateTime() {
        return updateTime.getString("com.das.chat.last_update_time.invites", "0");
    }

    public String getLastGeneralUpdateTime(String chatRoomId) {
        return updateTime.getString("com.das.chat.last_update_time.general_" + chatRoomId, session.getTime());
    }

    public String getLastRoomUpdateMessageId(String chatRoomId) {
        return updateTime.getString("com.das.chat.last_update_time.room_" + chatRoomId, "-1");
    }

    public ArrayList<ChatUser> getUsers() {
        return users;
    }

    public ArrayList<ChatRoom> getRooms() {
        return rooms;
    }

    public void updateRoomAlert(String roomId, boolean alert) {

        if(rooms == null)
            return;

        for (ChatRoom room : rooms) {
            if (room.getIdSala().compareTo(roomId) == 0) {
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

    public void logout(final OnWSResponseListener<Boolean> responseListener)
    {
        ChatWSTask task = new ChatWSTask();
        WSParams params = new WSParams();

        HttpPost post = new HttpPost(String.format("%s%s", WS_BASE_URL, WS_LOGOUT_URL));
        Log.d("REQUEST", String.format("%s%s", WS_BASE_URL, WS_LOGOUT_URL));

        params.setRequest(post);
        params.addTokenHeader(session.getSessionToken());

        task.setResponseListener(new OnWSResponseListener<String>()
        {
            @Override
            public void onWSResponse(final String response, final long errorCode, final String errorMsg) {
                if (errorMsg == null)
                {
                    users = null;
                    rooms = null;
                    session = null;
                    removeAllEnterRoomMessageId();
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

    public void changeChatRoomState(EnterChatRoomRequest req, final OnWSResponseListener<Boolean> responseListener)
    {
        ChatWSTask task = new ChatWSTask();
        WSParams params = new WSParams();

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

    public void getUpdates(final String idSala, final OnWSResponseListener<ChatUpdate> responseListener)
    {
        ChatWSTask task = new ChatWSTask();
        WSParams params = new WSParams();

        HttpGet get = new HttpGet(String.format("%s%s/sala/%s?ultima_act=%s", WS_BASE_URL, WS_UPDATES_URL, idSala, getLastGeneralUpdateTime(idSala)));
        Log.d("REQUEST", String.format("%s%s/sala/%s?ultima_act=%s", WS_BASE_URL, WS_UPDATES_URL, idSala, getLastGeneralUpdateTime(idSala)));

        params.setRequest(get);
        params.addTokenHeader(session.getSessionToken());

        task.setResponseListener(new OnWSResponseListener<String>() {
            @Override
            public void onWSResponse(final String response, final long errorCode, final String errorMsg) {
                if (errorMsg == null && !response.isEmpty()) {
                    ChatUpdate update = GetUpdatesResponse.initWithResponse(response);
                    responseListener.onWSResponse(update, errorCode, null);
                    Backend.getInstance().setLastGeneralUpdateTime(idSala);
                } else {
                    responseListener.onWSResponse(null, errorCode, errorMsg);
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

    public void getChatRoomMessages(final EnterChatRoomRequest req, final String messageId, final OnWSResponseListener<ArrayList<ChatMessage>> responseListener)
    {

        ChatWSTask task = new ChatWSTask();
        WSParams params = new WSParams();

        HttpGet get = new HttpGet(String.format("%s%s/sala/%s?ultimo_mensaje=%s", WS_BASE_URL, WS_MESSAGES_URL, req.getIdSala(), messageId));
        Log.d("REQUEST", String.format("%s%s/sala/%s?ultimo_mensaje=%s", WS_BASE_URL, WS_MESSAGES_URL, req.getIdSala(), messageId));
        params.setRequest(get);
        params.addTokenHeader(session.getSessionToken());

        task.setResponseListener(new OnWSResponseListener<String>() {
            @Override
            public void onWSResponse(final String response, final long errorCode, final String errorMsg) {
                if (errorMsg == null) {

                    ArrayList<ChatMessage> messages = EnterChatRoomGetMessagesResponse.initWithResponse(response);
                    if(messages.size() > 0) {
                        Backend.getInstance().setLastRoomUpdateMessageId(req.getIdSala(), messages.get(messages.size() -1).getIdMessage());
                    }

                    if(!messageId.equalsIgnoreCase("-1") || (messages.size() > 0 && messages.get(messages.size() -1).getUser().getUserId().equalsIgnoreCase(session.getUserId()))) {
                        responseListener.onWSResponse(messages, errorCode, null);
                    } else {
                        responseListener.onWSResponse(new ArrayList<ChatMessage>(), errorCode, null);
                    }
                } else {
                    responseListener.onWSResponse(null, errorCode, errorMsg);
                }
            }
        });
        task.execute(params);
    }

    public void getChatRoomLastMessage(final EnterChatRoomRequest req, final OnWSResponseListener<ChatMessage> responseListener)
    {
        ChatWSTask task = new ChatWSTask();
        WSParams params = new WSParams();

        HttpGet get = new HttpGet(String.format("%s%s/sala/%s?ultimo_mensaje=-1", WS_BASE_URL, WS_MESSAGES_URL, req.getIdSala()));
        Log.d("REQUEST", String.format("%s%s/sala/%s?ultimo_mensaje=-1", WS_BASE_URL, WS_MESSAGES_URL, req.getIdSala()));
        params.setRequest(get);
        params.addTokenHeader(session.getSessionToken());

        task.setResponseListener(new OnWSResponseListener<String>() {
            @Override
            public void onWSResponse(final String response, final long errorCode, final String errorMsg) {
                if (errorMsg == null) {
                    ArrayList<ChatMessage> messages = EnterChatRoomGetMessagesResponse.initWithResponse(response);
                    responseListener.onWSResponse(messages.get(0), errorCode, null);
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
                    Log.d("MESSAGES", "MESSAGES");
                    ArrayList<ChatMessage> messages = EnterChatRoomGetMessagesResponse.initWithResponse(response);
                    responseListener.onWSResponse(messages, errorCode, null);
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

    public void getRoomList(final OnWSResponseListener<Boolean> responseListener)
    {
        ChatWSTask task = new ChatWSTask();
        WSParams params = new WSParams();

        HttpGet get = new HttpGet(String.format("%s%s", WS_BASE_URL, WS_ROOMS_USERS_URL));

        params.setRequest(get);
        params.addTokenHeader(session.getSessionToken());

        task.setResponseListener(new OnWSResponseListener<String>()
        {
            @Override
            public void onWSResponse(final String response, final long errorCode, final String errorMsg) {
                if (errorMsg == null)
                {
                    rooms = ListRoomsResponse.initWithResponse(response);
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

    public void getInvitationList(String date, final OnWSResponseListener<ArrayList<ChatInvitation>> responseListener)
    {
        ChatWSTask task = new ChatWSTask();
        WSParams params = new WSParams();

        HttpGet get = new HttpGet(String.format("%s%s/%s?ultima_act=%s", WS_BASE_URL, WS_INVITATIONS_URL, session.getUserId(), 0));
        Log.d("REQUEST", String.format("%s%s/%s?ultima_act=%s", WS_BASE_URL, WS_INVITATIONS_URL, session.getUserId(), 0));
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

    public void sendInvitation(SendInvitationRequest req, final OnWSResponseListener<ChatInvitation> responseListener)
    {
        ChatWSTask task = new ChatWSTask();
        WSParams params = new WSParams();

        HttpPost post = new HttpPost(String.format("%s%s", WS_BASE_URL, WS_INVITATIONS_URL));
        Log.d("REQUEST", String.format("%s%s", WS_BASE_URL, WS_INVITATIONS_URL));

        params.setRequestWithBody(post, req.getForm());
        params.addTokenHeader(session.getSessionToken());
        Log.d("REQUEST", req.getForm());

        task.setResponseListener(new OnWSResponseListener<String>()
        {
            @Override
            public void onWSResponse(final String response, final long errorCode, final String errorMsg) {
                if (errorMsg == null)
                {
                    responseListener.onWSResponse(SendInvitationResponse.initWithResponse(response), errorCode, errorMsg);
                }
                else
                {
                    responseListener.onWSResponse(null, errorCode, errorMsg);
                }
            }
        });
        task.execute(params);
    }

    public void updateInvitation(UpdateInvitationRequest req, final OnWSResponseListener<Boolean> responseListener)
    {
        ChatWSTask task = new ChatWSTask();
        WSParams params = new WSParams();

        HttpPut put = new HttpPut(String.format("%s%s", WS_BASE_URL, WS_INVITATIONS_URL));
        Log.d("REQUEST", String.format("%s%s", WS_BASE_URL, WS_INVITATIONS_URL));

        params.setRequestWithBody(put, req.getForm());
        params.addTokenHeader(session.getSessionToken());
        Log.d("REQUEST", req.getForm());

        task.setResponseListener(new OnWSResponseListener<String>()
        {
            @Override
            public void onWSResponse(final String response, final long errorCode, final String errorMsg) {
                if (errorMsg == null)
                {
                    responseListener.onWSResponse(true, errorCode, errorMsg);
                }
                else
                {
                    responseListener.onWSResponse(false, errorCode, errorMsg);
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
        Log.d("REQUEST", String.format("%s%s", WS_BASE_URL, WS_ROOMS_URL));

        Log.d("REQUEST", req.getForm());

        params.setRequestWithBody(post, req.getForm());
        params.addTokenHeader(session.getSessionToken());

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
