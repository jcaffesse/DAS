package com.das.chat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.das.chat.R;

public class RegisterActivity extends AppCompatActivity {
    EditText usernameET;
    EditText passwordET;
    EditText repPasswordET;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameET = (EditText) findViewById(R.id.reg_username_et);
        passwordET = (EditText) findViewById(R.id.reg_rep_password_et);
        repPasswordET = (EditText) findViewById(R.id.reg_rep_password_et);
    }

    public void saveButtonPressed(View v)
    {
        Toast.makeText(this, usernameET.getText() + " " + passwordET.getText() + " " + repPasswordET.getText(), Toast.LENGTH_SHORT).show();
    }

    public void backButtonPressed(View v)
    {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}
