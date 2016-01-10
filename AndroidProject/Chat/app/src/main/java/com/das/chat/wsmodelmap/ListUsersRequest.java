package com.das.chat.wsmodelmap;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListUsersRequest
{
    public static ArrayList<String> initWithResponse(String response)
    {
        ArrayList<String> roomList = new ArrayList<String>();

        JSONObject obj = null;
        try
        {
            obj = new JSONObject(response);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return roomList;
    }

}
