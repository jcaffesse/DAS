package com.das.chat.application;

import android.app.Application;
import android.content.Context;



public class ChatApplication extends Application {

    private static Context context;

    public void onCreate(){
        super.onCreate();
        ChatApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return ChatApplication.context;
    }
}
