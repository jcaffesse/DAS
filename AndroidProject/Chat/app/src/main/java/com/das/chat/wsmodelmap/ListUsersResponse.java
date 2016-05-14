package com.das.chat.wsmodelmap;

import com.das.chat.dao.ChatUser;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ListUsersResponse
{
    public static ArrayList<ChatUser> initWithResponse(String response)
    {
        ArrayList<ChatUser> usersArray = new ArrayList<ChatUser>();

        JSONArray resp = null;

        try {
            resp = new JSONArray(response);
            for(int i=0; i<resp.length(); i++)
            {
                ChatUser user = new ChatUser();

                user.setUserName(resp.getJSONObject(i).getString("nombre_usuario"));
                user.setUserEmail(resp.getJSONObject(i).getString("email_usuario"));
                user.setUserId(resp.getJSONObject(i).getString("id_usuario"));
                user.setUserRolId(resp.getJSONObject(i).getString("rol_usuario"));
                usersArray.add(user);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return usersArray;
    }
}
