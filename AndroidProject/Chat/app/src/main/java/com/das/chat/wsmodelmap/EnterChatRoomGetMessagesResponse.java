package com.das.chat.wsmodelmap;

import com.das.chat.dao.ChatMessage;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Pablo on 27/09/2015.
 */
public class EnterChatRoomGetMessagesResponse {

    public static ArrayList<ChatMessage> initWithResponse(String response)
    {
        ArrayList<ChatMessage> messagesArray = new ArrayList<ChatMessage>();

        JSONArray resp = null;

        try {
            resp = new JSONArray(response);
            for(int i=0; i<resp.length(); i++)
            {
                ChatMessage message = new ChatMessage();

                message.setIdMessage(resp.getJSONObject(i).getString("id_mensaje"));
                message.setIdUser(resp.getJSONObject(i).getString("id_usuario"));
                message.setIdChatRoom(resp.getJSONObject(i).getString("id_sala"));
                message.setMessage(resp.getJSONObject(i).getString("mensaje"));
                message.setDate(resp.getJSONObject(i).getString("fecha_mensaje"));
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
                DateFormat endFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
                Date date=null;
                try {
                    date = format.parse(resp.getJSONObject(i).getString("fecha_mensaje"));
                } catch (Exception e) {

                }
                message.setDate(endFormat.format(date));
                messagesArray.add(message);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return messagesArray;
    }
}
