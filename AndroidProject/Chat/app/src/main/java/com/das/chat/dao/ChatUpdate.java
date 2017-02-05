package com.das.chat.dao;

import java.io.Serializable;


public class ChatUpdate implements Serializable{
    private String updateId;
    private String actionName;
    private String typeName;
    private String actionId;
    private String updateDate;
    private String roomId;

    public ChatUser getUser() {
        return user;
    }

    public void setUser(String userId, String userName, String userEmail, String userRolId) {
        this.user = new ChatUser(userId, userName, userEmail, userRolId);
    }

    private ChatUser user;

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
