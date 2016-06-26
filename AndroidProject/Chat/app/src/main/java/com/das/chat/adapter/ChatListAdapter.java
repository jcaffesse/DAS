package com.das.chat.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.das.chat.dao.ChatMessage;
import com.das.chat.R;
import com.das.chat.backend.Backend;
import com.das.chat.wsmodelmap.SendMessageRequest;

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

    public void updateChatList(ArrayList<ChatMessage> msgList)
    {
        if(msgList.size() == 0) {
            Log.d("LOG", "empty msg list");
            return;
        }

        Log.d("LOG", "check msg list");
        for (int i = messageList.size() -1; i >= 0; i--) {
            if (messageList.get(i).getDate() == null  && messageList.get(i).getIdUser().compareTo(Backend.getInstance().getSession().getUserId()) == 0) {
                Log.d("LOG", "message not sent yet");
                for (ChatMessage msg1 : msgList) {
                    if (msg1.getMessage().compareTo(messageList.get(i).getMessage()) == 0 && msg1.getIdUser().compareTo(messageList.get(i).getIdUser()) == 0)
                    {
                        Log.d("LOG", "removed and updated message: " + messageList.get(i).getMessage());
                        messageList.remove(i);
                    }
                }
            }
        }

        this.messageList.addAll(msgList);
        this.notifyDataSetChanged();
    }

    public void addMessage(SendMessageRequest req)
    {
        ChatMessage myMsg = new ChatMessage();
        myMsg.setMessage(req.getMessage());
        myMsg.setIdUser(req.getIdUsuario());
        myMsg.setIdChatRoom(req.getIdSala());
        this.messageList.add(myMsg);
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        ChatMessage message = messageList.get(i);
        view = inflater.inflate(R.layout.my_chat_message_item, null);

        if(message.getIdUser().compareTo(Backend.getInstance().getSession().getUserId()) != 0) {
            view = inflater.inflate(R.layout.their_chat_message_item, null);
            TextView tv = (TextView) view.findViewById(R.id.message_owner);
            tv.setText(Backend.getInstance().getUserById(message.getIdUser()).getUserName());
        }

        TextView tv = (TextView) view.findViewById(R.id.message_text);
        tv.setText(message.getMessage());
        TextView tv1 = (TextView) view.findViewById(R.id.message_date);
        if(message.getDate() == null) {
            tv1.setText("Enviando...");
        } else {
            tv1.setText(message.getDate());
        }

        return view;
    }
}
