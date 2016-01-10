package com.das.chat.backend;

import android.provider.Settings;

import com.das.chat.Model.Login;
import com.das.chat.application.ChatApplication;
import com.das.chat.wsmodelmap.LoginRequest;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class WSParams 
{
	private String body;

    HttpRequestBase req;

    public void setRequest(HttpRequestBase req)
    {
        this.req = req;
    }

    public void setRequestWithBody(HttpRequestBase req, String body)
    {
        this.req = req;

        AbstractHttpEntity entity = null;
        try
        {
            entity = new ByteArrayEntity(body.getBytes("UTF8"));

            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded"));

            ((HttpPost)this.req).setEntity(entity);
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }

    public void addAuthorizationHeader(LoginRequest req)
    {
        try
        {
            this.req.setHeader("X-ApiAuth-Userid", Settings.Secure.getString(ChatApplication.getAppContext().getContentResolver(), Settings.Secure.ANDROID_ID));
            this.req.setHeader("username", req.getUsername());
            this.req.setHeader("password", req.getPassword());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public void addTokenHeader(String token)
    {
        this.req.setHeader("Authorization", token);
    }

    public HttpRequestBase getRequest()
    {
        return this.req;
    }

}
