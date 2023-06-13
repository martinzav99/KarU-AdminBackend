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

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "g11_scoring", schema = "public")
public class CreditAnalisysDE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "g11_id", length = 8, nullable = false)
    private BigInteger id;
    @Column(name = "g11_document", length = 50, nullable = false)
    private String document;
    @Column(name = "g11_score", length = 10, nullable = false)
    private String value;
    
}
