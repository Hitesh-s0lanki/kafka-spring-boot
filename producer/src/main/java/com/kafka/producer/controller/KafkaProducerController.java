package com.kafka.producer.controller;

import com.kafka.producer.model.RiderLocation;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class KafkaProducerController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, RiderLocation> kafkaTemplateObject;

    public KafkaProducerController(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, RiderLocation> kafkaTemplateObject) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTemplateObject = kafkaTemplateObject;
    }

    @PostMapping("send")
    public String sendMessage(@RequestParam("message") String message){
        kafkaTemplate.send("my-topic", message);
        return "Message sent: "+ message;
    }

    @PostMapping("send/object")
    public String sendObject(@RequestParam("message") String message){

        RiderLocation location = new RiderLocation("rider123", 28.61, 90.2);

        kafkaTemplateObject.send("my-topic-new", location);
        return "Message sent: "+ message;
    }
}
