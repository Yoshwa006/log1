package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Payload {

    String event;
    String userID;
    String timestamp;


    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }



}
