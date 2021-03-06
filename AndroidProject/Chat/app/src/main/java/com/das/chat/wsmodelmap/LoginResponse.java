package com.das.chat.wsmodelmap;

import com.das.chat.dao.ChatUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class LoginResponse
{
    public static ChatUser initWithResponse(String response)
    {
        JSONObject resp = null;
        ChatUser login = new ChatUser();
        try {
            resp = new JSONObject(response);
            login.setUserName(resp.getString("nombre"));
            login.setUserEmail(resp.getString("email"));
            login.setUserId(resp.getString("id"));
            login.setUserRolId(resp.getString("idRol"));
            login.setSessionToken(resp.getString("Auth-Token"));
            login.setTime(Long.toString(new Date().getTime()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return login;
    }
}
