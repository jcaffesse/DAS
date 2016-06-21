package com.das.chat.dialog;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.das.chat.R;
import com.das.chat.dao.ChatInvitation;

public class InvitationDetailDialog extends DialogFragment {

    private ChatInvitation invite;

    public InvitationDetailDialog() {
    }

    public InvitationDetailDialog(ChatInvitation invite) {
        this.invite = invite;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_invitation_detail, container);
        if(invite != null) {
            TextView title = (TextView)view.findViewById(R.id.invitation_title);
            TextView message = (TextView)view.findViewById(R.id.invitation_message);
            Button acceptBtn = (Button)view.findViewById(R.id.accept_button);
            Button discardBtn = (Button)view.findViewById(R.id.discard_button);

            title.setText(invite.getInvitationReceiver().getUserName() + " te ha invitado a crear una sala");
            message.setText(invite.getInvitationMessage());

            acceptBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InvitationDetailDialog.this.dismiss();
                }
            });

            discardBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InvitationDetailDialog.this.dismiss();
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
}