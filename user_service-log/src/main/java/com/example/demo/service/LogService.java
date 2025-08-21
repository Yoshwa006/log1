package com.example.demo.service;

import com.example.demo.model.Load;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private final KafkaTemplate<String, Load> kafkaTemplate;

    @Autowired
    public LogService(KafkaTemplate<String, Load> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLog(String topic, Load load) {
        kafkaTemplate.send(topic, load);
        System.out.println("Log sent to Kafka: " + load);
    }
}
