package com.das.chat.dao;

import java.io.Serializable;

/**
 * Created by pablo on 12/20/15.
 */
public class ChatInvitation implements Serializable{
    private String invitationOwner;
    private String invitationReceiver;
    private String invitationDate;
    private String invitationMessage;
    private String invitationStatus;

    public String getInvitationOwner() {
        return invitationOwner;
    }

    public void setInvitationOwner(String invitationOwner) {
        this.invitationOwner = invitationOwner;
    }

    public String getInvitationReceiver() {
        return invitationReceiver;
    }

    public void setInvitationReceiver(String invitationReceiver) {
        this.invitationReceiver = invitationReceiver;
    }

    public String getInvitationDate() {
        return invitationDate;
    }

    public void setInvitationDate(String invitationDate) {
        this.invitationDate = invitationDate;
    }

    public String getInvitationMessage() {
        return invitationMessage;
    }

    public void setInvitationMessage(String invitationMessage) {
        this.invitationMessage = invitationMessage;
    }

    public String getInvitationStatus() {
        return invitationStatus;
    }

    public void setInvitationStatus(String invitationStatus) {
        this.invitationStatus = invitationStatus;
    }
}
