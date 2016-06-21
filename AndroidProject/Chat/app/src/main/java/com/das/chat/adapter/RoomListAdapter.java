package com.das.chat.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.das.chat.dao.ChatRoom;
import com.das.chat.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Pablo on 13/09/2015.
 */
public class RoomListAdapter extends BaseAdapter
{
    Context mContext;
    ArrayList<ChatRoom> mRooms;
    LayoutInflater mInflater;

    public RoomListAdapter(Context context, ArrayList<ChatRoom> rooms)
    {
        super();
        this.mContext = context;
        this.mRooms = rooms;
        this.mInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount()
    {
        return mRooms.size();
    }

    @Override
    public Object getItem(int i)
    {
        return mRooms.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    public void updateRoomList(ArrayList<ChatRoom> roomList)
    {
        this.mRooms = roomList;
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        ChatRoom room = mRooms.get(i);

        view = mInflater.inflate(R.layout.chat_room_list_item, null);
        TextView crName = (TextView) view.findViewById(R.id.chat_room_name);
        crName.setText(room.getNombreSala());

        TextView  crDesc = (TextView) view.findViewById(R.id.chat_room_desc);
        crDesc.setText(room.getDescSala());

        if(room.getAlertaSala()) {
            crName.setTextColor(mContext.getResources().getColor(android.R.color.holo_red_dark));
            Log.d("RoomListAdapter", "new messages in room " + room.getNombreSala() );
        }

        ImageView iv = (ImageView) view.findViewById(R.id.chat_room_image);
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        iv.setColorFilter(color);

        return view;
    }
}
