package com.das.chat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.das.chat.Model.ChatUser;
import com.das.chat.R;

import java.util.ArrayList;

/**
 * Created by Pablo on 13/09/2015.
 */
public class UserListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<ChatUser> users;
    LayoutInflater inflater;

    public UserListAdapter(Context context, ArrayList<ChatUser> users)
    {
        super();
        this.context = context;
        this.users = users;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount()
    {
        return users.size();
    }

    @Override
    public Object getItem(int i)
    {
        return users.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    public void updateRoomList(ArrayList<ChatUser> usersList)
    {
        this.users = usersList;
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        view = inflater.inflate(R.layout.chat_room_list_item, null);
        TextView tv = (TextView) view.findViewById(R.id.message_text);
        tv.setText(users.get(i).getUserName());

        return view;
    }
}
