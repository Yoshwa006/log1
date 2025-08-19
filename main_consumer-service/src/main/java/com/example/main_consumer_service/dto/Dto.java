package com.example.main_consumer_service.dto;

import com.example.main_consumer_service.model.Log;
import com.example.main_consumer_service.model.Payload;

public class Dto {
    public static Log toLog(Payload payload) {
        Log log = new Log();
        log.setServiceName(payload.getServiceName());
        log.setEvent(payload.getEvent());
        log.setUserID(payload.getUserID());
        log.setTimestamp(payload.getTimestamp());
        log.setMessage(payload.getMessage());
        log.setLevel(payload.getLevel());
        return log;
    }
}
