package com.das.chat.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.das.chat.R;
import com.das.chat.dao.ChatInvitation;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Pablo on 13/09/2015.
 */
public class InvitationListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<ChatInvitation> invites;
    LayoutInflater inflater;

    public InvitationListAdapter(Context context, ArrayList<ChatInvitation> invites)
    {
        super();
        this.context = context;
        this.invites = invites;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return invites.size();
    }

    @Override
    public Object getItem(int i)
    {
        return invites.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        view = inflater.inflate(R.layout.invites_list_item, null);
        TextView uName = (TextView) view.findViewById(R.id.invitation_name);
        uName.setText(invites.get(i).getInvitationSender().getUserName());

        TextView message = (TextView) view.findViewById(R.id.invitation_message);
        message.setText(invites.get(i).getInvitationMessage());

        return view;
    }
}
