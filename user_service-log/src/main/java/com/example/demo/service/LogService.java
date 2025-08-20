package com.example.demo.service;

import com.example.demo.model.Load;
import com.example.demo.model.Payload;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private final KafkaTemplate<String, Load> kafkaTemplate;
    private final KafkaTemplate<String, String> test;

    public LogService(KafkaTemplate<String, Load> kafkaTemplate , KafkaTemplate<String, String> test) {
        this.test = test;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLog(String topic, Load load) {
        kafkaTemplate.send(topic, load);
        System.out.println("📤 Log sent to Kafka: " + load);
    }

    public void test(String message){
        System.out.println(message);
        test.send("logs", message);
    }

}
