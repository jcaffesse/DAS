package com.das.chat.wsmodelmap;

import com.das.chat.dao.ChatInvitation;
import com.das.chat.dao.ChatUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SendInvitationResponse
{
    public static ChatInvitation initWithResponse(String response)
    {
        JSONObject resp = null;
        ChatInvitation invite = null;
        try {
            resp = new JSONObject(response);

            invite = new ChatInvitation();
            invite.setInvitationOwner(resp.getString("id_destino"));

            ChatUser user = new ChatUser();
            JSONObject usrJson = resp.getJSONObject("usuario");
            user.setUserId(usrJson.getString("id_usuario"));
            user.setUserName(usrJson.getString("nombre_usuario"));
            user.setUserEmail(usrJson.getString("email_usuario"));
            invite.setInvitationSender(user);

            invite.setInvitationDate(resp.getString("fecha_invitacion"));
            invite.setInvitationMessage(resp.getString("mensaje_invitacion"));
            invite.setInvitationStatus(resp.getString("estado"));
            invite.setInvitationChatRoom(resp.getString("id_sala"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return invite;
    }
}