package com.kafka.producer.controller;


import com.kafka.producer.model.RiderLocation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Configuration
public class KafkaProducerStreamController {

    @Bean
    public Supplier<RiderLocation> sendRiderLocation(){
        return () -> {
            RiderLocation location = new RiderLocation("rider4623", 88.9, 99.90);
            System.out.println("Sending: "+location.getRiderId());
            return location;
        };
    }
}
