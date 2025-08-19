package com.example.main_consumer_service.controller;

import com.example.main_consumer_service.model.Payload;
import com.example.main_consumer_service.service.ConsumerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/main")
public class Controller {

    private final ConsumerService service;


    public Controller(ConsumerService service) {
        this.service = service;
    }

    @PostMapping("/consume")
    public String consume(@RequestBody Payload payload){
        service.consumeAndStore(payload);
        return "Payload consumed and stored successfully";
    }

}
