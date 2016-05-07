package com.das.chat.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.das.chat.Model.ChatMessage;
import com.das.chat.Model.ChatRoom;
import com.das.chat.Model.ChatUser;
import com.das.chat.R;
import com.das.chat.adapter.RoomListAdapter;
import com.das.chat.backend.Backend;
import com.das.chat.backend.OnWSResponseListener;
import com.das.chat.wsmodelmap.EnterChatRoomRequest;

import java.util.ArrayList;

/**
 * Created by pablo on 1/10/16.
 */
public class RoomListFragment extends Fragment{

    ListView list;
    RoomListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_room_list, container, false);
        Bundle args = getArguments();

        list = (ListView) rootView.findViewById(R.id.list_view);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                final EnterChatRoomRequest req = new EnterChatRoomRequest();
                final ChatRoom chatRoom = (ChatRoom) adapterView.getItemAtPosition(i);
                req.setIdSala(chatRoom.getIdSala());
                Backend.getInstance().enterChatRoom(req, new OnWSResponseListener<ArrayList<ChatUser>>() {
                    @Override
                    public void onWSResponse(final ArrayList<ChatUser> response1, long errorCode, final String errorMsg) {
                        if (errorMsg == null) {
                            Backend.getInstance().getChatRoomMessages(req, new OnWSResponseListener<ArrayList<ChatMessage>>() {
                                @Override
                                public void onWSResponse(ArrayList<ChatMessage> response, long errorCode, String errorMsg) {
                                    if (errorMsg == null) {
                                        Intent i = new Intent(getActivity(), ChatActitivy.class);
                                        i.putExtra("users", response1);
                                        i.putExtra("messages", response);
                                        i.putExtra("chatroom", chatRoom);
                                        startActivity(i);
                                    }
                                }
                            });

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
        Backend.getInstance().getRoomList(new OnWSResponseListener<ArrayList<ChatRoom>>() {
            @Override
            public void onWSResponse(ArrayList<ChatRoom> response, long errorCode, final String errorMsg) {
                if (errorMsg == null) {
                    adapter = new RoomListAdapter(getActivity(), response);
                    list.setAdapter(adapter);
                }
            }
        });
    }
}
