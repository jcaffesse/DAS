package com.das.chat.backend;

import com.das.chat.Model.ChatRoom;
import com.das.chat.Model.ChatUser;
import com.das.chat.wsmodelmap.AddRoomRequest;
import com.das.chat.wsmodelmap.EnterChatRoomRequest;
import com.das.chat.wsmodelmap.EnterChatRoomResponse;
import com.das.chat.wsmodelmap.ListRoomsResponse;
import com.das.chat.wsmodelmap.ListUsersResponse;
import com.das.chat.wsmodelmap.LoginRequest;
import com.das.chat.wsmodelmap.LoginResponse;
import com.das.chat.wsmodelmap.RegisterRequest;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import java.util.ArrayList;

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

    private static Backend instance = new Backend();
    private ChatUser session;

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

    public void enterChatRoom(EnterChatRoomRequest req, final OnWSResponseListener<ArrayList<ChatUser>> responseListener)
    {
        ChatWSTask task = new ChatWSTask();
        WSParams params = new WSParams();

        HttpPost post = new HttpPost(String.format("%s%s", WS_BASE_URL, WS_USERS_ROOMS_URL));

        params.setRequestWithBody(post, req.getForm());
        params.addTokenHeader("asdasdasd");

        task.setResponseListener(new OnWSResponseListener<String>()
        {
            @Override
            public void onWSResponse(final String response, final long errorCode, final String errorMsg) {
                if (errorMsg == null)
                {
                    ArrayList<ChatUser> users = EnterChatRoomResponse.initWithResponse(response);
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

    public void getRoomList(final OnWSResponseListener<ArrayList<ChatRoom>> responseListener)
    {
        ChatWSTask task = new ChatWSTask();
        WSParams params = new WSParams();

        HttpGet get = new HttpGet(String.format("%s%s", WS_BASE_URL, WS_ROOMS_URL));

        params.setRequest(get);
        params.addTokenHeader("asdasdasd");

        task.setResponseListener(new OnWSResponseListener<String>()
        {
            @Override
            public void onWSResponse(final String response, final long errorCode, final String errorMsg) {
                if (errorMsg == null)
                {
                    ArrayList<ChatRoom> rooms = ListRoomsResponse.initWithResponse(response);
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

    public void getUserList(final OnWSResponseListener<ArrayList<ChatUser>> responseListener)
    {
        ChatWSTask task = new ChatWSTask();
        WSParams params = new WSParams();

        HttpGet get = new HttpGet(String.format("%s%s", WS_BASE_URL, WS_USERS_URL));

        params.setRequest(get);
        params.addTokenHeader("asdasdasd");

        task.setResponseListener(new OnWSResponseListener<String>()
        {
            @Override
            public void onWSResponse(final String response, final long errorCode, final String errorMsg) {
                if (errorMsg == null)
                {
                    ArrayList<ChatUser> users = ListUsersResponse.initWithResponse(response);
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
