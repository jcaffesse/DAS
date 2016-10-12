package com.das.chat.wsmodelmap;

import com.das.chat.dao.ChatInvitation;
import com.das.chat.dao.ChatUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetInvitationsResponse
{
    public static ArrayList<ChatInvitation> initWithResponse(String response)
    {
        ArrayList<ChatInvitation> invitesArray = new ArrayList<ChatInvitation>();

        JSONArray resp = null;

        try {
            resp = new JSONArray(response);
            for(int i=0; i<resp.length(); i++) {
                ChatInvitation invite = new ChatInvitation();
                invite.setInvitationOwner(resp.getJSONObject(i).getString("id_destino"));

                ChatUser user = new ChatUser();
                JSONObject usrJson = resp.getJSONObject(i).getJSONObject("usuario");
                user.setUserId(usrJson.getString("id_usuario"));
                user.setUserName(usrJson.getString("nombre_usuario"));
                user.setUserEmail(usrJson.getString("email_usuario"));
                invite.setInvitationSender(user);

                invite.setInvitationDate(resp.getJSONObject(i).getString("fecha_invitacion"));
                invite.setInvitationMessage(resp.getJSONObject(i).getString("mensaje_invitacion"));
                invite.setInvitationStatus(resp.getJSONObject(i).getString("estado"));
                invite.setInvitationChatRoom(resp.getJSONObject(i).getString("id_sala"));
                invitesArray.add(invite);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return invitesArray;
    }
}
