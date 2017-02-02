package com.das.chat.activity;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.content.Intent;

import com.das.chat.R;
import com.das.chat.adapter.RoomListAdapter;
import com.das.chat.backend.Backend;
import com.das.chat.backend.OnWSResponseListener;
import com.das.chat.dao.ChatInvitation;
import com.das.chat.dao.ChatRoom;
import com.das.chat.wsmodelmap.AddRoomRequest;
import com.das.chat.wsmodelmap.LoginRequest;

import java.util.ArrayList;

public class LoginActivity extends Activity
{
    EditText usernameET;
    EditText passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameET = (EditText) findViewById(R.id.username_et);
        passwordET = (EditText) findViewById(R.id.password_et);

        usernameET.setText("javier");
        passwordET.setText("qwerty");
    }

    public void loginButtonPressed(View v) {
        callLoginWS();
    }

    public void registerButtonPressed(View v) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    @Override
    protected void onStop() {
        super.onStop();
        showLoadingView(false);
    }

    public void callLoginWS() {
        LoginRequest req = new LoginRequest();
        req.setUsername(usernameET.getText().toString());
        req.setPassword(passwordET.getText().toString());
        showLoadingView(true);

        Backend.getInstance().login(req, new OnWSResponseListener<Boolean>() {
            @Override
            public void onWSResponse(Boolean response, long errorCode, final String errorMsg) {
                if (errorMsg == null) {

                    Backend.getInstance().getRoomList(new OnWSResponseListener<Boolean>() {
                        @Override
                        public void onWSResponse(Boolean response, long errorCode, final String errorMsg) {
                            if (errorMsg == null) {
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                showLoadingView(false);
                            }

                        }
                    });

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "ChatUser incorrecto", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    public void showLoadingView (final boolean show) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(show) {
                    findViewById(R.id.loading_layout).setVisibility(View.VISIBLE);
                } else {
                    findViewById(R.id.loading_layout).setVisibility(View.GONE);
                }
            }
        });
    }
}
