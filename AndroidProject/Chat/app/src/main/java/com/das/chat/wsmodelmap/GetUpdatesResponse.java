package com.das.chat.wsmodelmap;

import com.das.chat.dao.ChatUpdate;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class GetUpdatesResponse {

    public static ArrayList<ChatUpdate> initWithResponse(String response)
    {
        ArrayList<ChatUpdate> updatesArray = new ArrayList<>();

        JSONArray resp;

        try {
            resp = new JSONArray(response);
            for(int i=0; i<resp.length(); i++)
            {
                ChatUpdate update = new ChatUpdate();

                update.setUpdateId(resp.getJSONObject(i).getString("id_actualizacion"));
                update.setActionName(resp.getJSONObject(i).getString("nombre_accion"));
                update.setTypeName(resp.getJSONObject(i).getString("nombre_tipo"));
                update.setActionId(resp.getJSONObject(i).getString("id_dato"));
                update.setUpdateDate(resp.getJSONObject(i).getString("fecha_actualizacion"));
                update.setRoomId(resp.getJSONObject(i).getString("id_sala"));
                if(resp.getJSONObject(i).has("usuario")) {
                    update.setUser(resp.getJSONObject(i).getJSONObject("usuario").getString("id_usuario"),
                            resp.getJSONObject(i).getJSONObject("usuario").getString("nombre_usuario"),
                            resp.getJSONObject(i).getJSONObject("usuario").getString("email_usuario"),
                            resp.getJSONObject(i).getJSONObject("usuario").getString("rol_usuario"));
                }
                updatesArray.add(update);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return updatesArray;
    }
}
