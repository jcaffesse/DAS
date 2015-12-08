package com.das.chat.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.das.chat.Model.ChatRoom;
import com.das.chat.R;
import com.das.chat.adapter.RoomListAdapter;
import com.das.chat.backend.Backend;
import com.das.chat.backend.OnWSResponseListener;
import com.das.chat.wsmodelmap.AddRoomRequest;

import java.util.ArrayList;

public class RoomListActivity extends Activity {

    ListView list;
    RoomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
        list = (ListView) findViewById(R.id.list_view);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("ROOMS LIST ACTIVITY", adapter.getItem(i).toString());
            }
        });
    }

    public void onAddRoomPressed(View v)
    {
        Intent i  = new Intent(this, AddRoomActivity.class);
        startActivity(i);
    }

    public void onLogoutPressed(View v)
    {

    }

    @Override
    protected void onResume()
    {
        super.onResume();

        Backend.getInstance().getRoomList(new OnWSResponseListener<ArrayList<ChatRoom>>() {
            @Override
            public void onWSResponse(ArrayList<ChatRoom> response, long errorCode, final String errorMsg) {
            if (errorMsg == null)
            {
                adapter = new RoomListAdapter(RoomListActivity.this, response);
                list.setAdapter(adapter);
            }
            }
        });
    }
}
