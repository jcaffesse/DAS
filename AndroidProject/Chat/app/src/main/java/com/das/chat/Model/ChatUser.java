package com.das.chat.Model;

import java.io.Serializable;

/**
 * Created by pablo on 12/20/15.
 */
public class ChatUser implements Serializable{
    private String userId;
    private String userName;
    private String userEmail;
    private String userRolId;

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserRolId() {
        return userRolId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserRolId(String userRolId) {
        this.userRolId = userRolId;
    }
}
