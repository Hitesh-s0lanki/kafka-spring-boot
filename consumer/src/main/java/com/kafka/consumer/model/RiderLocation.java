package com.kafka.consumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiderLocation {

    private String riderId;
    private double latitude;
    private double longitude;

}
