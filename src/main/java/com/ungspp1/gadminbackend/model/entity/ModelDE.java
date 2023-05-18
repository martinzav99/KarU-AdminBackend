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
    private BigInteger id;
    @Column(name = "g06_brand", length = 20, nullable = false)
    private String brand;
    @Column(name = "g06_model", length = 20, nullable = false)
    private String model;
    @Column(name = "g06_year", length = 4, nullable = false)
    private String year;
    @Column(name = "g06_base_price", nullable = false)
    private Float basePrice;
    
}
