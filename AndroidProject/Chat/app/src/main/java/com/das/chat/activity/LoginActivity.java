package com.das.chat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.das.chat.R;

public class LoginActivity extends AppCompatActivity
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
        Toast.makeText(this, usernameET.getText() + " " + passwordET.getText(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, RoomListActivity.class);
        startActivity(i);
    }

    public void registerButtonPressed(View v)
    {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

}
