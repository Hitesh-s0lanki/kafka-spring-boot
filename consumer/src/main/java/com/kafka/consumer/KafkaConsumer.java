package com.kafka.consumer;

import com.kafka.consumer.model.RiderLocation;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "my-topic", groupId = "my-new-group")
    public void listen1(String message){
        System.out.println("Received Message 1: "+ message);
    }

    @KafkaListener(topics = "my-topic", groupId = "my-new-group2")
    public void listen2(String message){
        System.out.println("Received Message 2 : "+ message);
    }

    @KafkaListener(topics = "my-topic-new", groupId = "my-group")
    public void listen3(RiderLocation location){
        System.out.println("Received Message 2 : "+ location.getRiderId());
    }

}
