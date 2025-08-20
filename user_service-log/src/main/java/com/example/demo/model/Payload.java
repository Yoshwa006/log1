package com.example.demo.model;

import org.springframework.stereotype.Service;

@Service
public class Payload {

    String userID;
    String event;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }




}
