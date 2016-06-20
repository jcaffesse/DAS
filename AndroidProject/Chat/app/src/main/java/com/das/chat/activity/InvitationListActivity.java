package com.das.chat.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.das.chat.R;
import com.das.chat.adapter.InvitationListAdapter;
import com.das.chat.backend.Backend;
import com.das.chat.backend.OnWSResponseListener;
import com.das.chat.dao.ChatInvitation;

import java.util.ArrayList;

public class InvitationListActivity extends Activity {

    InvitationListAdapter adapter;
    ListView invitesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation_list);
        invitesListView = (ListView) findViewById(R.id.invites_list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        showLoadingView(true);
        Backend.getInstance().getInvitationList("2", "0", new OnWSResponseListener<ArrayList<ChatInvitation>>() {
            @Override
            public void onWSResponse(ArrayList<ChatInvitation> response, long errorCode, String errorMsg) {
                if (errorMsg == null) {
                    adapter = new InvitationListAdapter(InvitationListActivity.this, response);
                    invitesListView.setAdapter(adapter);
                }
                showLoadingView(false);
            }
        });
    }

    public void showLoadingView (final boolean show) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (show) {
                    findViewById(R.id.loading_layout).setVisibility(View.VISIBLE);
                } else {
                    findViewById(R.id.loading_layout).setVisibility(View.GONE);
                }
            }
        });
    }
}
