package com.ungspp1.gadminbackend.api.vehicle.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModelTO {
    private String brand;
    private String model;
    private String year;
    private String fuelType;
    private String engine;
    private Float basePrice;
}
