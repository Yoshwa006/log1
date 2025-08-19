package com.example.main_consumer_service.service;

import com.example.main_consumer_service.dto.Dto;
import com.example.main_consumer_service.model.Payload;
import com.example.main_consumer_service.model.Log;
import com.example.main_consumer_service.repo.LogRepo;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private final LogRepo logRepo;

    public ConsumerService(LogRepo logRepo) {
        this.logRepo = logRepo;
    }

    @KafkaListener(topics = "logs", groupId = "g1")
    public void consumeAndStore(Payload payload) {

        System.out.println("RAW PAYLOAD: " + payload);
        System.out.println("serviceName=" + payload.getServiceName());
        System.out.println("event=" + payload.getEvent());
        System.out.println("userId=" + payload.getUserID());
        System.out.println("timestamp=" + payload.getTimestamp());
        System.out.println("level=" + payload.getLevel());
        System.out.println("message=" + payload.getMessage());
        Log log = Dto.toLog(payload);
        logRepo.save(log);
    }


    public void consumeAndStoreTest(String message) {
        System.out.println("Consumed and stored test log" + message);
    }
}
