package com.das.chat.dialog;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.das.chat.R;
import com.das.chat.backend.Backend;
import com.das.chat.backend.OnWSResponseListener;
import com.das.chat.dao.ChatInvitation;
import com.das.chat.dao.ChatUser;
import com.das.chat.wsmodelmap.SendInvitationRequest;

public class SendInvitationDialog extends DialogFragment {

    private ChatUser usr;

    public SendInvitationDialog() {
    }

    @SuppressLint("ValidFragment")
    public SendInvitationDialog(ChatUser user) {
        this.usr = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_send_invitation, container);
        if(usr != null) {
            TextView title = (TextView)view.findViewById(R.id.invitation_rcvr);
            Button sendBtn = (Button)view.findViewById(R.id.send_button);
            Button cancelBtn = (Button)view.findViewById(R.id.cancel_button);

            title.setText(usr.getUserName());

            sendBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendInvitation();

                }
            });

            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SendInvitationDialog.this.dismiss();
                }
            });
        }

        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    public void sendInvitation() {

        SendInvitationRequest req = new SendInvitationRequest();
        req.setIdUsuario(Backend.getInstance().getSession().getUserId());
        req.setIdDestino(usr.getUserId());
        req.setMensaje("Que te parece si chateamos en una sala privada?");

        Backend.getInstance().sendInvitation(req, new OnWSResponseListener<Boolean>() {
            @Override
            public void onWSResponse(Boolean response, long errorCode, String errorMsg) {
                if (errorMsg == null) {

                } else {

                }
            }
        });

        SendInvitationDialog.this.dismiss();
    }
}