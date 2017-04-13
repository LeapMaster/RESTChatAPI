package com.sudowrestlers.chatapi.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by student on 3/23/17.
 */
@Entity
@Table(name="users")
public class User {


    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="ID")
    private Integer userId;

    @Column(name="username")
    private String username;
    private String user;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(){

    }

    public void setUser(String user) {
        this.user = user;
    }



    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                "username=" + username +
                "}";
    }



}