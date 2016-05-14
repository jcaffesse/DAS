package com.das.chat.wsmodelmap;

import com.das.chat.dao.ChatRoom;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ListRoomsResponse
{
    public static ArrayList<ChatRoom> initWithResponse(String response)
    {
        ArrayList<ChatRoom> roomArray = new ArrayList<ChatRoom>();

        JSONArray resp = null;

        try {
            resp = new JSONArray(response);
            for(int i=0; i<resp.length(); i++)
            {
                ChatRoom room = new ChatRoom();
                room.setIdSala(resp.getJSONObject(i).getString("id_sala"));
                room.setNombreSala(resp.getJSONObject(i).getString("nombre_sala"));
                room.setDescSala(resp.getJSONObject(i).getString("desc_sala"));
                room.setTipoSala(resp.getJSONObject(i).getString("tipo_sala"));
                roomArray.add(room);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return roomArray;
    }
}
