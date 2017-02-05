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
import android.widget.Button;
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

public class ChatActivity extends Activity implements GeneralUpdateService.ChatRoomCallbacks, GeneralUpdateService.ChatRoomUpdatesCallbacks
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
    Button userListButton;
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
        userListButton = (Button) findViewById(R.id.user_list_btn);


        users = (ArrayList<ChatUser>)getIntent().getSerializableExtra("users");

        for(ChatUser user : users ) {
            if(user.getUserId().compareTo(Backend.getInstance().getSession().getUserId()) == 0) {
                users.remove(user);
                break;
            }
        }

        messages = (ArrayList<ChatMessage>)getIntent().getSerializableExtra("messages");
        chatRoom = (ChatRoom) getIntent().getSerializableExtra("chatroom");

        numberOfUsers.setText(Integer.toString(users.size()) + " usuarios en la sala");

        adapter = new ChatListAdapter(this, messages);
        messageList.setAdapter(adapter);
        chatRoomImage.setColorFilter(chatRoom.getColor());
        chatRoomName.setText(chatRoom.getNombreSala());
        userListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChatActivity.this, UserListActivity.class);
                i.putExtra("users", users);
                startActivity(i);
            }
        });
        //aca hay que meter el id del ultimo mensaje de la sala

    }

    private void removeUser(String userId) {
        for(ChatUser user : users ) {
            if(user.getUserId().compareTo(userId) == 0) {
                users.remove(user);
                numberOfUsers.setText(Integer.toString(users.size()) + " usuarios en la sala");
                break;
            }
        }
    }

    private void addUser(ChatUser user) {
        users.add(user);
        numberOfUsers.setText(Integer.toString(users.size()) + " usuarios en la sala");
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
            serviceInstante.registerChatRoomClient(ChatActivity.this, chatRoom);
            serviceInstante.registerChatRoomUpdatesClient(ChatActivity.this, chatRoom);
            serviceInstante.startChatRoomTimer();
            serviceInstante.startChatRoomUpdatesTimer();
        }

        public void onServiceDisconnected(ComponentName className) {
            Log.d("MainActivity", "----------- onServiceDisconnected ------------");
            serviceInstante = null;
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
                    Backend.getInstance().removeEnterRoomMessageId(chatRoom.getIdSala());
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ChatActivity.this, "Error al abandonar la sala", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(ChatActivity.this, "Error al enviar mensaje", Toast.LENGTH_SHORT).show();
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
    public void updateUpdatesForChatRoom(ArrayList<ChatUpdate> updates) {
        boolean shouldFinish = false;

        if(updates != null) {
            for (ChatUpdate update : updates) {
                if(update.getActionName().equalsIgnoreCase("delete") && update.getTypeName().equalsIgnoreCase("mensaje")) {
                    adapter.deleteMessage(update.getActionId());
                }
                if(update.getActionName().equalsIgnoreCase("create") && update.getTypeName().equalsIgnoreCase("usuariosala")) {
                    addUser(update.getUser());
                } else if(update.getActionName().equalsIgnoreCase("delete") && update.getTypeName().equalsIgnoreCase("usuariosala")) {
                    if(update.getActionId().equalsIgnoreCase(Backend.getInstance().getSession().getUserId())) {
                        Backend.getInstance().removeEnterRoomMessageId(chatRoom.getIdSala());
                        shouldFinish = true;
                    } else {
                        removeUser(update.getActionId());
                    }
                }
            }
        }
        if(shouldFinish) {
            finish();
        }
    }
}