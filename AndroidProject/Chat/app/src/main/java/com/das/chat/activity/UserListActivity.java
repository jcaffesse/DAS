package com.das.chat.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.das.chat.R;
import com.das.chat.adapter.UserListAdapter;
import com.das.chat.backend.Backend;
import com.das.chat.dao.ChatUser;
import com.das.chat.dialog.SendInvitationDialog;

import java.util.ArrayList;

public class UserListActivity extends FragmentActivity {

    private ListView userListListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        userListListView = (ListView)findViewById(R.id.list_view);

        ArrayList<ChatUser> userList = Backend.getInstance().getUsers();
        ArrayList<ChatUser> curUsers = (ArrayList) getIntent().getSerializableExtra("users");
        for(ChatUser user : userList ) {
            for(ChatUser curUser : curUsers) {
                if(user.getUserId().compareTo(curUser.getUserId()) == 0) {
                    userList.remove(user);
                    break;
                }
            }
        }

        final UserListAdapter adapter = new UserListAdapter(this, userList);
        userListListView.setAdapter(adapter);

        userListListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showInviteDialog((ChatUser)adapter.getItem(i));
            }
        });
    }

    private void showInviteDialog(ChatUser usr) {
        FragmentManager fm = getSupportFragmentManager();
        SendInvitationDialog editNameDialog = new SendInvitationDialog(usr);
        editNameDialog.show(fm, "fragment_invitation");
    }


}
