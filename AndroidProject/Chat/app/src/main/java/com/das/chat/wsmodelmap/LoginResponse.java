package com.das.chat.wsmodelmap;

import com.das.chat.Model.ChatRoom;
import com.das.chat.Model.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginResponse
{
    public static Login initWithResponse(String response)
    {
        JSONObject resp = null;
        Login login = new Login();
        try {
            resp = new JSONObject(response);
            login.setUserName(resp.getString("nombre"));
            login.setUserEmail(resp.getString("email"));
            login.setUserId(resp.getString("id"));
            login.setUserRolId(resp.getString("idRol"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return login;
    }
}
