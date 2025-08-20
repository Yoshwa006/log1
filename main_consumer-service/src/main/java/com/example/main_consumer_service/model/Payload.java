package com.example.main_consumer_service.model;

public class Payload {

    //json inside the log
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
