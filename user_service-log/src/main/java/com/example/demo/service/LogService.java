package com.example.demo.service;

import com.example.demo.model.Payload;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private final KafkaTemplate<String, Payload> kafkaTemplate;
    private final KafkaTemplate<String, String> test;

    public LogService(KafkaTemplate<String, Payload> kafkaTemplate , KafkaTemplate<String, String> test) {
        this.test = test;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLog(String topic, Payload payload) {
        kafkaTemplate.send(topic, payload);
        System.out.println("📤 Log sent to Kafka: " + payload);
    }

    public void test(String message){
        System.out.println(message);
        test.send("logs", message);
    }

}
