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
    @Column(name = "g07_id", length = 8, nullable = false)
    private BigInteger id;
    @Column(name = "g07_title", nullable = false)
    private Boolean title;
    @Column(name = "g07_cedula", nullable = false)
    private Boolean cedula; 
    @Column(name = "g07_cuit", nullable = false)
    private Boolean cuit;
    @Column(name = "g07_historical", nullable = false)
    private Boolean historical;
    @Column(name = "g07_infractions", nullable = false)
    private Boolean infractions;
    @Column(name = "g07_faults_debt", nullable = false)
    private Float debt;
    @Column(name = "g07_vpa", nullable = false)
    private Boolean vpa;
    @Column(name = "g07_rva", nullable = false)
    private Boolean rva;
    @Column(name = "g07_vtv", nullable = false)
    private Boolean vtv;
    
}