package com.das.chat.activity;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.das.chat.R;
import com.das.chat.backend.Backend;
import com.das.chat.backend.OnWSResponseListener;
import com.das.chat.wsmodelmap.AddRoomRequest;
import com.das.chat.wsmodelmap.LoginRequest;

public class LoginActivity extends Activity
{
    EditText usernameET;
    EditText passwordET;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameET = (EditText) findViewById(R.id.username_et);
        passwordET = (EditText) findViewById(R.id.password_et);
    }

    public void loginButtonPressed(View v)
    {
        //Toast.makeText(this, usernameET.getText() + " " + passwordET.getText(), Toast.LENGTH_SHORT).show();
        //Intent i = new Intent(this, RoomListActivity.class);
        //startActivity(i);
        callLoginWS();
    }

    public void registerButtonPressed(View v)
    {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    public void callLoginWS()
    {
        LoginRequest req = new LoginRequest();
        req.setUsername("Pablo");
        req.setPassword("qwerty");

        Backend.getInstance().login(req, new OnWSResponseListener<Boolean>() {
            @Override
            public void onWSResponse(Boolean response, long errorCode, final String errorMsg) {
                if (errorMsg == null) {
                    Toast.makeText(LoginActivity.this, "ChatUser correcto", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "ChatUser incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
