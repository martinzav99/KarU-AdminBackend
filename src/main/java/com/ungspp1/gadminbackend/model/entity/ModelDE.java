package com.ungspp1.gadminbackend.model.entity;

import java.math.BigInteger;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "g06_model", schema = "public")
public class ModelDE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "g06_id", length = 8, nullable = false)
    private BigInteger modelId;
    @Column(name = "g06_brand", length = 20, nullable = false)
    private String brand;
    @Column(name = "g06_model", length = 20, nullable = false)
    private String model;
    @Column(name = "g06_year", length = 4, nullable = false)
    private String year;
    @Column(name = "g06_base_price", nullable = false)
    private Float basePrice;
    @Column(name = "g06_fuel_type", length = 7, nullable = false)
    private String fuelType;
    @Column(name = "g06_engine", length = 60,nullable = false)
    private String engine;
    @Column(name = "g06_category", length = 20 ,nullable = false)
    private String category;
    
}
