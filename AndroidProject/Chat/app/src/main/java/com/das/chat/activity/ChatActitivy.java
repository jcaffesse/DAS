package com.das.chat.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.das.chat.dao.ChatMessage;
import com.das.chat.dao.ChatRoom;
import com.das.chat.dao.ChatUser;
import com.das.chat.R;
import com.das.chat.adapter.ChatListAdapter;
import com.das.chat.backend.Backend;
import com.das.chat.backend.OnWSResponseListener;
import com.das.chat.wsmodelmap.EnterChatRoomRequest;
import com.das.chat.wsmodelmap.SendMessageRequest;

import java.util.ArrayList;

public class ChatActitivy extends Activity {

    ArrayList<ChatUser> users;
    ArrayList<ChatMessage> messages;
    ChatRoom chatRoom;
    TextView numberOfUsers;
    EditText messageET;
    ListView messageList;
    ChatListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        messageList = (ListView) findViewById(R.id.message_list);
        numberOfUsers = (TextView) findViewById(R.id.number_of_users);
        messageET = (EditText) findViewById(R.id.enter_message_et);

        users = (ArrayList<ChatUser>)getIntent().getSerializableExtra("users");
        messages = (ArrayList<ChatMessage>)getIntent().getSerializableExtra("messages");
        chatRoom = (ChatRoom) getIntent().getSerializableExtra("chatroom");

        numberOfUsers.setText(Integer.toString(users.size()) + " usuarios en la sala");

        adapter = new ChatListAdapter(this, messages);
        messageList.setAdapter(adapter);
    }


    public void onSendButtonClicked(View v) {
        if(messageET.getText().length() == 0)
            return;

        SendMessageRequest req = new SendMessageRequest();
        req.setMessage(messageET.getText().toString());
        req.setIdUsuario(Backend.getInstance().getSession().getUserId());
        req.setIdSala(chatRoom.getIdSala());

        Backend.getInstance().sendMessage(req, new OnWSResponseListener<Boolean>() {
            @Override
            public void onWSResponse(Boolean response, long errorCode, String errorMsg) {
                if (errorMsg == null) {
                    EnterChatRoomRequest req = new EnterChatRoomRequest();
                    req.setIdSala(chatRoom.getIdSala());
                    Backend.getInstance().getChatRoomMessages(req, new OnWSResponseListener<ArrayList<ChatMessage>>() {
                        @Override
                        public void onWSResponse(ArrayList<ChatMessage> response, long errorCode, String errorMsg) {
                            if (errorMsg == null) {
                                adapter.updateChatList(response);
                                messageET.setText("");
                            }
                        }
                    });
                } else {
                    Toast.makeText(ChatActitivy.this, "Error al enviar mensaje", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onBackButtonPressed(View v) {
        finish();
    }
}
