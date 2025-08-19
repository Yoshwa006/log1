package com.example.main_consumer_service.service;

import com.example.main_consumer_service.model.Payload;
import com.example.main_consumer_service.model.Log;
import com.example.main_consumer_service.repo.LogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ConsumerService {


    private KafkaTemplate<String, Log> kafkaTemplate;
    private final LogRepo logRepo;

    public ConsumerService(LogRepo logRepo) {
        this.logRepo = logRepo;
    }

    @KafkaListener(topics = "logs", groupId = "g1")
    public void consumeAndStore(Payload payload) {
        Log log1 = new Log();
        log1.setUserID(payload.getUserID());
        log1.setServiceName(payload.getServiceName());
        log1.setEvent(payload.getEvent());
        log1.setTimestamp(payload.getTimestamp());
        log1.setLevel(payload.getLevel());
        logRepo.save(log1);
        kafkaTemplate.send("logs", log1);
    }
}