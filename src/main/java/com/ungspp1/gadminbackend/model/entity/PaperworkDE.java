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
@Table(name = "g07_paperwork", schema = "public")
public class PaperworkDE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "g07_id", length = 8, nullable = true)
    private BigInteger paperworkId;
    @Column(name = "g07_infractions", nullable = true)
    private Boolean infractions;
    @Column(name = "g07_faults_debt", nullable = true)
    private Float debt;
    @Column(name = "g07_vpa", nullable = true)
    private Boolean vpa;
    @Column(name = "g07_rva", nullable = true)
    private Boolean rva;
    @Column(name = "g07_vtv", nullable = true)
    private Boolean vtv;
    
}