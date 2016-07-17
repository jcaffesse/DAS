package com.das.chat.activity;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.das.chat.R;
import com.das.chat.backend.Backend;
import com.das.chat.backend.OnWSResponseListener;
import com.das.chat.wsmodelmap.RegisterRequest;

public class RegisterActivity extends Activity {
    EditText usernameET;
    EditText emailET;
    EditText passwordET;
    EditText repPasswordET;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameET = (EditText) findViewById(R.id.reg_username_et);
        emailET = (EditText) findViewById(R.id.reg_email_et);
        passwordET = (EditText) findViewById(R.id.reg_password_et);
    }

    public void registerButtonPressed(View v)
    {
        onRegisterPressed();
    }

    public void onRegisterPressed()
    {
        RegisterRequest req = new RegisterRequest();
        req.setUsername(usernameET.getText().toString());
        req.setEmail(emailET.getText().toString());
        req.setPassword(passwordET.getText().toString());

        Backend.getInstance().register(req, new OnWSResponseListener<Boolean>() {
            @Override
            public void onWSResponse(Boolean response, long errorCode, final String errorMsg) {
                if (errorMsg == null) {
                    Toast.makeText(RegisterActivity.this, "Registro correcto", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Registro incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}