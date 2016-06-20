package com.das.chat.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.das.chat.dao.ChatInvitation;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_room_list, container, false);
        list = (ListView) rootView.findViewById(R.id.list_view);

        showLoadingView(true);
        Backend.getInstance().getRoomList(new OnWSResponseListener<ArrayList<ChatRoom>>() {
            @Override
            public void onWSResponse(ArrayList<ChatRoom> response, long errorCode, final String errorMsg) {
                if (errorMsg == null) {
                    adapter = new RoomListAdapter(getActivity(), response);
                    list.setAdapter(adapter);
                }
                showLoadingView(false);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                final EnterChatRoomRequest req = new EnterChatRoomRequest();
                final ChatRoom chatRoom = (ChatRoom) adapterView.getItemAtPosition(i);
                req.setIdSala(chatRoom.getIdSala());
                ((MainActivity) getActivity()).showLoadingView(true);
                Backend.getInstance().enterChatRoom(req, new OnWSResponseListener<ArrayList<ChatUser>>() {
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
                                    }

                                    ((MainActivity) getActivity()).showLoadingView(false);
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

    public void showLoadingView (final boolean show) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (show) {
                    rootView.findViewById(R.id.loading_layout).setVisibility(View.VISIBLE);
                } else {
                    rootView.findViewById(R.id.loading_layout).setVisibility(View.GONE);
                }
            }
        });
    }
}
