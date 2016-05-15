package com.das.chat.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.das.chat.dao.ChatUser;
import com.das.chat.R;
import com.das.chat.adapter.UserListAdapter;
import com.das.chat.backend.Backend;
import com.das.chat.backend.OnWSResponseListener;

import java.util.ArrayList;

public class UserListFragment extends Fragment{

    ListView list;
    UserListAdapter adapter;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_user_list, container, false);

        list = (ListView) rootView.findViewById(R.id.list_view);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("ROOMS LIST ACTIVITY", adapter.getItem(i).toString());
            }
        });

        showLoadingView(true);
        Backend.getInstance().getUserList(new OnWSResponseListener<ArrayList<ChatUser>>() {
            @Override
            public void onWSResponse(ArrayList<ChatUser> response, long errorCode, final String errorMsg) {
                if (errorMsg == null) {
                    adapter = new UserListAdapter(getActivity(), response);
                    list.setAdapter(adapter);
                }
                showLoadingView(false);
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void showLoadingView (final boolean show) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (show) {
                    rootView.findViewById(R.id.loading_layout).setVisibility(View.VISIBLE);
                } else {
                    rootView.findViewById(R.id.loading_layout).setVisibility(View.GONE);
                }
            }
        });
    }
}