package com.example.demo.service;

import com.example.demo.model.Payload;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private final KafkaTemplate<String, Payload> kafkaTemplate;

    public LogService(KafkaTemplate<String, Payload> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLogForRegister(String topic, Payload payload) {
        kafkaTemplate.send(topic, payload);
        System.out.println("📤 Log sent to Kafka: " + payload);
    }

    public void sendLogForLogin(String topic, Payload payload) {
        kafkaTemplate.send(topic, payload);
        System.out.println("📤 Log sent to Kafka: " + payload);
    }

}
