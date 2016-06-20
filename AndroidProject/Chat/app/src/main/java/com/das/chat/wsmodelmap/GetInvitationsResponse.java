package com.das.chat.wsmodelmap;

import com.das.chat.dao.ChatInvitation;
import com.das.chat.dao.ChatRoom;

import org.json.JSONArray;
import org.json.JSONException;

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
                invite.setInvitationOwner(resp.getJSONObject(i).getString("id_usuario"));
                invite.setInvitationOwner(resp.getJSONObject(i).getString("id_destino"));
                invite.setInvitationDate(resp.getJSONObject(i).getString("fecha_invitacion"));
                invite.setInvitationMessage(resp.getJSONObject(i).getString("mensaje_invitacion"));
                invite.setInvitationStatus(resp.getJSONObject(i).getString("estado"));
                invitesArray.add(invite);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return invitesArray;
    }
}
