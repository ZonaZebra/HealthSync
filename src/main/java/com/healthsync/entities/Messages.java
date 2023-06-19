package com.healthsync.entities;

import java.util.Date;

public class Messages {
    private String messageID; // this should be unique
    private String senderID; // this will reference userID from User table
    private String receiverID; // this will reference userID from User table
    private Date dateSent;
    private String subject;
    private String message;

    // Constructors
    public Messages() {
    }

    public Messages(String messageID, String senderID, String receiverID, Date dateSent, String subject, String message) {
        this.messageID = messageID;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.dateSent = dateSent;
        this.subject = subject;
        this.message = message;
    }

    // Getters
    public String getMessageID() {
        return messageID;
    }

    public String getSenderID() {
        return senderID;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    // Setters
    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
