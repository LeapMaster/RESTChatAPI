package com.sudowrestlers.chatapi.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by student on 3/6/17.
 */
@Entity@Table(name="messages")
public class Message {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="ID")
    public int ID;

    @Column(name="message")
    public String message;

    @Column(name="timestamp")
    private Timestamp timestamp;

    @Column(name="userID", nullable=true)
    private Integer userID;
    public Message next;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Message{" +
                "ID:" + ID +
                ",message:" + message +
                ",timestamp:" + timestamp +
                ",userID:" + userID +
                "}";
    }

}
