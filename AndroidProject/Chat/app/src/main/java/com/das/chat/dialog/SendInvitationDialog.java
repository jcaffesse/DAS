package com.das.chat.dialog;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
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
import com.das.chat.activity.ChatActitivy;
import com.das.chat.activity.InvitationListActivity;
import com.das.chat.activity.MainActivity;
import com.das.chat.backend.Backend;
import com.das.chat.backend.OnWSResponseListener;
import com.das.chat.dao.ChatInvitation;
import com.das.chat.dao.ChatMessage;
import com.das.chat.dao.ChatRoom;
import com.das.chat.dao.ChatUser;
import com.das.chat.wsmodelmap.AddRoomRequest;
import com.das.chat.wsmodelmap.EnterChatRoomRequest;
import com.das.chat.wsmodelmap.SendInvitationRequest;

import java.util.ArrayList;

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
                    SendInvitationDialog.this.getDialog().hide();
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
        req.setMensaje("Que te parece");

        ((MainActivity) this.getActivity()).showLoadingView(true);

        Backend.getInstance().sendInvitation(req, new OnWSResponseListener<ChatInvitation>() {
            @Override
            public void onWSResponse(final ChatInvitation responseInvitation, long errorCode, String errorMsg) {
                if (errorMsg == null) {


                    if(errorMsg == null) {
                        Backend.getInstance().getRoomList(new OnWSResponseListener<Boolean>() {
                            @Override
                            public void onWSResponse(Boolean response, long errorCode, final String errorMsg) {

                                final EnterChatRoomRequest req = new EnterChatRoomRequest();

                                req.setIdSala(responseInvitation.getInvitationChatRoom());
                                req.setIdUsuario(Backend.getInstance().getSession().getUserId());
                                req.setEstado("1");

                                Backend.getInstance().changeChatRoomState(req, new OnWSResponseListener<Boolean>() {
                                    @Override
                                    public void onWSResponse(Boolean response, long errorCode, String errorMsg) {
                                        if (errorMsg == null) {
                                            Backend.getInstance().getChatRoomUsers(req, new OnWSResponseListener<ArrayList<ChatUser>>() {
                                                @Override
                                                public void onWSResponse(final ArrayList<ChatUser> response1, long errorCode, final String errorMsg) {
                                                    if (errorMsg == null) {
                                                        Backend.getInstance().getChatRoomMessages(req, Backend.getInstance().getEnterRoomTime(responseInvitation.getInvitationChatRoom()), new OnWSResponseListener<ArrayList<ChatMessage>>() {
                                                            @Override
                                                            public void onWSResponse(ArrayList<ChatMessage> response, long errorCode, String errorMsg) {
                                                                if (errorMsg == null) {

                                                                    ((MainActivity) SendInvitationDialog.this.getActivity()).showLoadingView(false);
                                                                    SendInvitationDialog.this.dismiss();

                                                                    Intent i = new Intent(getActivity(), ChatActitivy.class);
                                                                    i.putExtra("users", response1);
                                                                    i.putExtra("messages", response);

                                                                    i.putExtra("chatroom", Backend.getInstance().getChatRoomById(responseInvitation.getInvitationChatRoom()));
                                                                    startActivity(i);
                                                                } else {
                                                                    ((MainActivity) getActivity()).showLoadingView(false);
                                                                }
                                                            }
                                                        });
                                                    } else {
                                                        ((MainActivity) getActivity()).showLoadingView(false);
                                                    }
                                                }
                                            });
                                        } else {
                                            ((MainActivity) getActivity()).showLoadingView(false);
                                        }
                                    }
                                });
                            }
                        });
                    }
                    else {
                        ((MainActivity) SendInvitationDialog.this.getActivity()).showLoadingView(false);
                        SendInvitationDialog.this.dismiss();
                    }
                } else {
                    ((MainActivity) SendInvitationDialog.this.getActivity()).showLoadingView(false);
                    SendInvitationDialog.this.dismiss();
                }
            }
        });


    }
}