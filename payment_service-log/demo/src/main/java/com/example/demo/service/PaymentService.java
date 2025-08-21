package com.example.demo.service;

import com.example.demo.model.Load;
import com.example.demo.model.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PaymentService {

    private final KafkaTemplate<String, Load> kafkaTemplate;

    @Autowired
    public PaymentService(KafkaTemplate<String, Load> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private void sendLog(String message, String level, Payload payload) {
        Load log = new Load();
        log.setServiceName("payment-service");
        log.setTimestamp(Instant.now().toString());
        log.setMessage(message);
        log.setLevel(level);
        log.setPayload(payload);

        kafkaTemplate.send("logs", log);
        System.out.println("📤 Log sent to Kafka: " + log);
    }

    public void handlePayment(Load load) {
        Payload payload = load.getPayload();
        String status = payload.getStatus();
        switch (status.toUpperCase()) {
            case "SUCCESS":
                sendLog(
                        "Payment completed successfully",
                        "INFO",
                        payload);
                break;
            case "FAILED":
                sendLog(
                        "Payment failed",
                        "ERROR",
                        payload);
                break;
            case "PENDING":
                sendLog(
                        "Payment is pending",
                        "WARN",
                        payload);
                break;
            default:
                sendLog(
                        "Unknown payment status",
                        "ERROR",
                        payload);
        }
    }
}
