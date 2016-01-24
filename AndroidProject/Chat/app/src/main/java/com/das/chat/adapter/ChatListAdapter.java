package com.das.chat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.das.chat.Model.ChatMessage;
import com.das.chat.Model.ChatRoom;
import com.das.chat.R;

import java.util.ArrayList;

/**
 * Created by Pablo on 13/09/2015.
 */
public class ChatListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<ChatMessage> messageList;
    LayoutInflater inflater;

    public ChatListAdapter(Context context, ArrayList<ChatMessage> messages)
    {
        super();
        this.context = context;
        this.messageList = messages;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return messageList.size();
    }

    @Override
    public Object getItem(int i)
    {
        return messageList.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    public void updateChatList(ArrayList<ChatMessage> messageList)
    {
        this.messageList = messageList;
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if(i%2 == 0)
        view = inflater.inflate(R.layout.chat_room_list_item, null);
        else
            view = inflater.inflate(R.layout.chat_room_their_message_item, null);
        TextView tv = (TextView) view.findViewById(R.id.room_name);
        tv.setText(messageList.get(i).getMessage());

        return view;
    }
}
