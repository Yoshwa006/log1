package com.example.main_consumer_service.service;

import com.example.main_consumer_service.dto.Mapper;
import com.example.main_consumer_service.model.LoadData;
import com.example.main_consumer_service.model.Log;
import com.example.main_consumer_service.model.Payload;
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
    public void consumeAndStore(LoadData loadData) {

        System.out.println("RAW PAYLOAD: " + loadData);
        System.out.println("serviceName=" + loadData.getServiceName());
        System.out.println("event=" + loadData.getEvent());
        System.out.println("userId=" + loadData.getUserID());
        System.out.println("timestamp=" + loadData.getTimestamp());
        System.out.println("level=" + loadData.getLevel());
        System.out.println("message=" + loadData.getMessage());
        Payload payload = new Payload();
        payload.setEvent(loadData.getEvent());
        payload.setUserID(loadData.getUserID());
        Log log = Mapper.toLog(loadData);
        logRepo.save(log);
    }


    public void consumeAndStoreTest(String message) {
        System.out.println("Consumed and stored test log" + message);
    }
}
