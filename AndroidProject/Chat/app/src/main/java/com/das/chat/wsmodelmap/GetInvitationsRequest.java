package com.das.chat.wsmodelmap;

import com.das.chat.dao.ChatInvitation;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class GetInvitationsRequest
{
    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    private String lastUpdate;

    public String getForm()
    {
        String res = new String();

        res += "ultima_act=";
        res += lastUpdate;

        return res;
    }


}
