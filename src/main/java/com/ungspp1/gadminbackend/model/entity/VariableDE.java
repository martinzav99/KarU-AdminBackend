package com.ungspp1.gadminbackend.model.entity;

import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "g09_variables", schema = "public")
public class VariableDE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "g09_id", length = 8, nullable = false)
    private BigInteger id;
    @Column(name = "g09_name", length = 30, nullable = false)
    private String name;
    @Column(name = "g09_value", nullable = false)
    private float value;
}
