package com.das.chat.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.das.chat.Model.ChatRoom;
import com.das.chat.R;

import java.util.ArrayList;

/**
 * Created by Pablo on 13/09/2015.
 */
public class RoomListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<ChatRoom> rooms;
    LayoutInflater inflater;

    public RoomListAdapter(Context context, ArrayList<ChatRoom> rooms)
    {
        super();
        this.context = context;
        this.rooms = rooms;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount()
    {
        return rooms.size();
    }

    @Override
    public Object getItem(int i)
    {
        return rooms.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    public void updateRoomList(ArrayList<ChatRoom> roomList)
    {
        this.rooms = roomList;
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        view = inflater.inflate(R.layout.chat_room_list_item, null);
        TextView tv = (TextView) view.findViewById(R.id.room_name);
        tv.setText(rooms.get(i).getNombreSala());

        return view;
    }
}
