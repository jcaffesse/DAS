package com.das.chat.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.das.chat.Model.ChatMessage;
import com.das.chat.Model.ChatUser;
import com.das.chat.R;
import com.das.chat.adapter.ChatListAdapter;

import java.util.ArrayList;

public class ChatActitivy extends Activity {

    ArrayList<ChatUser> users;
    TextView numberOfUsers;
    ListView messageList;
    ChatListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        messageList = (ListView) findViewById(R.id.message_list);

        ArrayList<ChatMessage> messages = new ArrayList<>();

        ChatMessage message = new ChatMessage();
        message.setMessage("Hola");
        messages.add(message);

        ChatMessage message1 = new ChatMessage();
        message1.setMessage("Hola mensaje 1");
        messages.add(message1);

        ChatMessage message2 = new ChatMessage();
        message2.setMessage("Hola mensaje 2");
        messages.add(message2);

        ChatMessage message3 = new ChatMessage();
        message3.setMessage("Hola mensaje 3");
        messages.add(message3);

        ChatMessage message4 = new ChatMessage();
        message4.setMessage("Hola mensaje 3");
        messages.add(message4);

        ChatMessage message5 = new ChatMessage();
        message5.setMessage("Hola mensaje 3");
        messages.add(message5);

        ChatMessage message6 = new ChatMessage();
        message6.setMessage("Hola mensaje 3");
        messages.add(message6);

        ChatMessage message7 = new ChatMessage();
        message7.setMessage("Hola mensaje 3");
        messages.add(message7);

        adapter = new ChatListAdapter(this, messages);
        messageList.setAdapter(adapter);


        //users = (ArrayList<ChatUser>)getIntent().getSerializableExtra("users");
        //numberOfUsers = (TextView) findViewById(R.id.number_of_users);
        //numberOfUsers.setText(Integer.toString(users.size()));
    }


}
