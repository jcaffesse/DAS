package com.das.chat.dao;

import java.io.Serializable;

/**
 * Created by pablo on 1/24/16.
 */
public class ChatMessage implements Serializable{

    private String idMessage;
    private String idChatRoom;
    private ChatUser user;
    private String message;
    private String date;

    public String getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(String idMessage) {
        this.idMessage = idMessage;
    }

    public String getIdChatRoom() {
        return idChatRoom;
    }

    public void setIdChatRoom(String idChatRoom) {
        this.idChatRoom = idChatRoom;
    }

    public ChatUser getUser() {
        return user;
    }

    public void setUser(ChatUser user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
