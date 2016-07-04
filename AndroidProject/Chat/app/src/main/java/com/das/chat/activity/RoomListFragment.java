package com.das.chat.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.das.chat.dao.ChatMessage;
import com.das.chat.dao.ChatRoom;
import com.das.chat.dao.ChatUser;
import com.das.chat.R;
import com.das.chat.adapter.RoomListAdapter;
import com.das.chat.backend.Backend;
import com.das.chat.backend.OnWSResponseListener;
import com.das.chat.wsmodelmap.EnterChatRoomRequest;

import java.util.ArrayList;

public class RoomListFragment extends Fragment{

    ListView list;
    RoomListAdapter adapter;
    View rootView;

    @Override
    public void onStart() {
        super.onStart();
        adapter = new RoomListAdapter(getActivity(), Backend.getInstance().getRooms());
        list.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_room_list, container, false);
        list = (ListView) rootView.findViewById(R.id.list_view);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                final EnterChatRoomRequest req = new EnterChatRoomRequest();
                final ChatRoom chatRoom = (ChatRoom) adapterView.getItemAtPosition(i);

                req.setIdSala(chatRoom.getIdSala());
                req.setIdUsuario(Backend.getInstance().getSession().getUserId());
                req.setEstado("1");

                ((MainActivity) getActivity()).showLoadingView(true);
                Backend.getInstance().changeChatRoomState(req, new OnWSResponseListener<Boolean>() {
                    @Override
                    public void onWSResponse(Boolean response, long errorCode, String errorMsg) {
                        if (errorMsg == null) {
                            Backend.getInstance().getChatRoomUsers(req, new OnWSResponseListener<ArrayList<ChatUser>>() {
                                @Override
                                public void onWSResponse(final ArrayList<ChatUser> response1, long errorCode, final String errorMsg) {
                                    if (errorMsg == null) {
                                        Backend.getInstance().getChatRoomMessages(req, "0", new OnWSResponseListener<ArrayList<ChatMessage>>() {
                                            @Override
                                            public void onWSResponse(ArrayList<ChatMessage> response, long errorCode, String errorMsg) {
                                                if (errorMsg == null) {
                                                    Intent i = new Intent(getActivity(), ChatActitivy.class);
                                                    i.putExtra("users", response1);
                                                    i.putExtra("messages", response);
                                                    i.putExtra("chatroom", chatRoom);
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

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((MainActivity) getActivity()).showLoadingView(false);
    }
}
