package com.example.main_consumer_service.dto;

import com.example.main_consumer_service.model.Log;
import com.example.main_consumer_service.model.LoadData;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Mapper {


    public static Log toLog(LoadData load) {
        Log log = new Log();
        log.setServiceName(load.getServiceName());
        log.setLevel(load.getLevel());
        log.setMessage(load.getMessage());
        log.setTimestamp(load.getTimestamp());

        try {
            ObjectMapper mapper = new ObjectMapper();
            String payloadJson = mapper.writeValueAsString(load.getPayload());
            log.setPayload(payloadJson);
        } catch (Exception e) {
            log.setPayload("{}"); // fallback
            e.printStackTrace();
        }

        return log;
    }

}
