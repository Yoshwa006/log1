package com.example.main_consumer_service.dto;

import com.example.main_consumer_service.model.Log;
import com.example.main_consumer_service.model.LoadData;


public class Mapper {
    public static Log toLog(LoadData loadData) {
        Log log = new Log();
        log.setServiceName(loadData.getServiceName());
        log.setEvent(loadData.getEvent());
        log.setUserID(loadData.getUserID());
        log.setTimestamp(loadData.getTimestamp());
        log.setMessage(loadData.getMessage());
        log.setLevel(loadData.getLevel());
        return log;
    }
}
