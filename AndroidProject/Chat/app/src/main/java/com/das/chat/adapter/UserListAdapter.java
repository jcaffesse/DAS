package com.das.chat.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.das.chat.dao.ChatUser;
import com.das.chat.R;

import java.util.ArrayList;
import java.util.Random;


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
        ChatUser user = users.get(i);

        view = inflater.inflate(R.layout.user_list_item, null);
        TextView uName = (TextView) view.findViewById(R.id.user_name);
        uName.setText(user.getUserName());

        TextView uEmail = (TextView) view.findViewById(R.id.user_email);
        uEmail.setText(user.getUserEmail());

        ImageView iv = (ImageView) view.findViewById(R.id.invite_img);
        iv.setColorFilter(user.getColor());

        return view;
    }
}
