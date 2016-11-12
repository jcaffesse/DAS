package com.das.chat.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.das.chat.dao.ChatMessage;
import com.das.chat.dao.ChatRoom;
import com.das.chat.dao.ChatUpdate;
import com.das.chat.dao.ChatUser;
import com.das.chat.R;
import com.das.chat.adapter.ChatListAdapter;
import com.das.chat.backend.Backend;
import com.das.chat.backend.OnWSResponseListener;
import com.das.chat.service.GeneralUpdateService;
import com.das.chat.wsmodelmap.EnterChatRoomRequest;
import com.das.chat.wsmodelmap.SendMessageRequest;

import java.util.ArrayList;

public class ChatActitivy extends Activity implements GeneralUpdateService.ChatRoomCallbacks, GeneralUpdateService.ChatRoomUpdatesCallbacks
{

    ArrayList<ChatUser> users;
    ArrayList<ChatMessage> messages;
    ChatRoom chatRoom;
    TextView numberOfUsers;
    TextView chatRoomName;
    EditText messageET;
    ListView messageList;
    ImageView chatRoomImage;
    ChatListAdapter adapter;
    private boolean serviceIsBind;
    private GeneralUpdateService serviceInstante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        messageList = (ListView) findViewById(R.id.message_list);
        numberOfUsers = (TextView) findViewById(R.id.number_of_users);
        messageET = (EditText) findViewById(R.id.enter_message_et);
        chatRoomImage = (ImageView) findViewById(R.id.chat_room_image);
        chatRoomName = (TextView) findViewById(R.id.chat_room_name);

        users = (ArrayList<ChatUser>)getIntent().getSerializableExtra("users");
        messages = (ArrayList<ChatMessage>)getIntent().getSerializableExtra("messages");
        chatRoom = (ChatRoom) getIntent().getSerializableExtra("chatroom");

        numberOfUsers.setText(Integer.toString(users.size()) + " usuarios en la sala");

        adapter = new ChatListAdapter(this, messages);
        messageList.setAdapter(adapter);
        chatRoomImage.setColorFilter(chatRoom.getColor());
        chatRoomName.setText(chatRoom.getNombreSala());
        //aca hay que meter el id del ultimo mensaje de la sala

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        bindService(new Intent(this, GeneralUpdateService.class), timerServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        serviceInstante.stopChatRoomTimer();
        serviceInstante.stopChatRoomUpdatesTimer();
        unbindService(timerServiceConnection);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Backend.getInstance().updateRoomAlert(chatRoom.getIdSala(), false);
    }

    private ServiceConnection timerServiceConnection = new ServiceConnection()
    {
        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.d("MainActivity", "------------- onServiceConnected -------------");

            serviceInstante =  ((GeneralUpdateService.LocalBinder) service).getService();
            serviceIsBind = true;
            serviceInstante.registerChatRoomClient(ChatActitivy.this, chatRoom);
            serviceInstante.registerChatRoomUpdatesClient(ChatActitivy.this, chatRoom);
            serviceInstante.startChatRoomTimer();
            serviceInstante.startChatRoomUpdatesTimer();
        }

        public void onServiceDisconnected(ComponentName className) {
            Log.d("MainActivity", "----------- onServiceDisconnected ------------");
            serviceInstante = null;
            serviceIsBind = false;
        }
    };

    public void onLeaveRoomButtonPressed(View v) {
        EnterChatRoomRequest req = new EnterChatRoomRequest();
        req.setIdUsuario(Backend.getInstance().getSession().getUserId());
        req.setIdSala(chatRoom.getIdSala());
        req.setEstado("0");

        Backend.getInstance().changeChatRoomState(req, new OnWSResponseListener<Boolean>() {
            @Override
            public void onWSResponse(Boolean response, long errorCode, String errorMsg) {
                if (errorMsg == null) {
                    Backend.getInstance().setLastRoomUpdateMessageId(chatRoom.getIdSala(), "");
                    Backend.getInstance().removeEnterRoomMessageId(chatRoom.getIdSala());
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ChatActitivy.this, "Error al abandonar la sala", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        finish();
    }

    public void onSendButtonClicked(View v) {
        if(messageET.getText().length() == 0)
            return;

        SendMessageRequest req = new SendMessageRequest();
        req.setMessage(messageET.getText().toString());
        req.setIdUsuario(Backend.getInstance().getSession().getUserId());
        req.setIdSala(chatRoom.getIdSala());

        adapter.addMessage(req);
        messageET.setText("");

        Backend.getInstance().sendMessage(req, new OnWSResponseListener<Boolean>() {
            @Override
            public void onWSResponse(Boolean response, long errorCode, String errorMsg) {
                if (errorMsg == null) {

                } else {
                    Toast.makeText(ChatActitivy.this, "Error al enviar mensaje", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onBackButtonPressed(View v) {
        finish();
    }

    @Override
    public void updateMessagesForChatRoom(ArrayList<ChatMessage> messages) {
        if(messages.size() > 0) {
            adapter.updateChatList(messages);
            Backend.getInstance().setLastRoomUpdateMessageId(chatRoom.getIdSala(), messages.get(messages.size()-1).getIdMessage());
        }
    }

    @Override
    public void updateUpdatesForChatRoom(ChatUpdate updates) {
        if(updates != null) {
            if(updates.getActionName().equalsIgnoreCase("delete") && updates.getTypeName().equalsIgnoreCase("mensaje")) {
                adapter.deleteMessage(updates.getActionId());
            } else if(updates.getActionName().equalsIgnoreCase("delete") && updates.getTypeName().equalsIgnoreCase("usuariosala") && updates.getActionId().equalsIgnoreCase(Backend.getInstance().getSession().getUserId())) {
                Backend.getInstance().setLastRoomUpdateMessageId(chatRoom.getIdSala(), "");
                Backend.getInstance().removeEnterRoomMessageId(chatRoom.getIdSala());
                finish();
            }
        }
    }
}