package com.das.chat.dialog;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.das.chat.R;
import com.das.chat.activity.InvitationListActivity;
import com.das.chat.activity.MainActivity;
import com.das.chat.backend.Backend;
import com.das.chat.backend.OnWSResponseListener;
import com.das.chat.dao.ChatInvitation;
import com.das.chat.dao.ChatUser;
import com.das.chat.wsmodelmap.SendInvitationRequest;
import com.das.chat.wsmodelmap.UpdateInvitationRequest;

public class InvitationDetailDialog extends DialogFragment {

    private ChatInvitation invite;

    public InvitationDetailDialog() {
    }

    @SuppressLint("ValidFragment")
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

            title.setText(invite.getInvitationSender().getUserName() + " te ha invitado a crear una sala");
            message.setText(invite.getInvitationMessage());

            acceptBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateInvitation("aceptada");
                    InvitationDetailDialog.this.getDialog().hide();
                }
            });

            discardBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateInvitation("cancelada");
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

    public void updateInvitation(String state) {

        UpdateInvitationRequest req = new UpdateInvitationRequest();
        req.setIdDestino(Backend.getInstance().getSession().getUserId());
        req.setIdUsuario(invite.getInvitationSender().getUserId());
        req.setEstado(state);

        ((InvitationListActivity)getActivity()).showLoadingView(true);

        Backend.getInstance().updateInvitation(req, new OnWSResponseListener<Boolean>() {
            @Override
            public void onWSResponse(Boolean response, long errorCode, String errorMsg) {
                if (errorMsg == null) {
                    Backend.getInstance().getRoomList(new OnWSResponseListener<Boolean>() {
                        @Override
                        public void onWSResponse(Boolean response, long errorCode, final String errorMsg) {
                            ((InvitationListActivity) getActivity()).showLoadingView(false);
                            InvitationDetailDialog.this.dismiss();
                        }
                    });

                } else {
                    ((InvitationListActivity) getActivity()).showLoadingView(false);
                    InvitationDetailDialog.this.dismiss();
                }
            }
        });


    }
}