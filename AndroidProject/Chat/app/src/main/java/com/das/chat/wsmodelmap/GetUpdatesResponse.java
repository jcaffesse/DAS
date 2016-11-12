package com.das.chat.wsmodelmap;

import com.das.chat.dao.ChatUpdate;
import com.das.chat.dao.ChatUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class GetUpdatesResponse {

    public static ChatUpdate initWithResponse(String response)
    {
        JSONObject resp = null;
        ChatUpdate update = new ChatUpdate();
        try {
            resp = new JSONObject(response);
            update.setUpdateId(resp.getString("id_actualizacion"));
            update.setActionName(resp.getString("nombre_accion"));
            update.setTypeName(resp.getString("nombre_tipo"));
            update.setActionId(resp.getString("id_dato"));
            update.setUpdateDate(resp.getString("fecha_actualizacion"));
            update.setRoomId(resp.getString("id_sala"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return update;
    }
}
