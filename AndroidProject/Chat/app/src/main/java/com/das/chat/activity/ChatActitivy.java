package com.das.chat.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.das.chat.Model.ChatUser;
import com.das.chat.R;

import java.util.ArrayList;

public class ChatActitivy extends Activity {

    ArrayList<ChatUser> users;
    TextView numberOfUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        users = (ArrayList<ChatUser>)getIntent().getSerializableExtra("users");
        numberOfUsers = (TextView) findViewById(R.id.number_of_users);
        numberOfUsers.setText(Integer.toString(users.size()));
    }
}
