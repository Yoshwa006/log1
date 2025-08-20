package com.example.main_consumer_service.service;

import com.example.main_consumer_service.dto.Mapper;
import com.example.main_consumer_service.model.LoadData;
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
    public void consumeAndStore(LoadData loadData) {

        System.out.println("RAW PAYLOAD: " + loadData);
        System.out.println("serviceName=" + loadData.getServiceName());
        System.out.println("timestamp=" + loadData.getTimestamp());
        System.out.println("level=" + loadData.getLevel());
        System.out.println("message=" + loadData.getMessage());


        Log finalLog = Mapper.toLog(loadData);
        logRepo.save(finalLog);
    }
}
