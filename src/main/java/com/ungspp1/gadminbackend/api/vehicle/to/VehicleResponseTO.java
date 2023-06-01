package com.ungspp1.gadminbackend.api.vehicle.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleResponseTO {
    private String plate;
    private String dni;
    private Float purchasePrice;
    private Float sellPrice;
    private String status;
    private float score;
    private float repairCost;
    private String branch;
    private String kilometers;
    private String message;
    private String brand;
    private String model;
    private Boolean gnc;
    private String fuelType;
    private String engine;
    private String origin;
    private String year;
    private Float basePrice;
    private Float debt;
    private Boolean vpa;
    private Boolean rva;
    private Boolean vtv;
    private String picture1;
    private String picture2;
    private String picture3;
}