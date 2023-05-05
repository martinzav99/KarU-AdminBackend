package com.ungspp1.gadminbackend.entity;

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
@Table(name = "g01_address", schema = "public")
public class AddressDE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "g01_id", length = 8, nullable = false)
    private BigInteger id;
    @Column(name = "g01_street", length = 20, nullable = false)
    private String street;
    @Column(name = "g01_street_number", length = 20, nullable = false)
    private String streetNumber;
    @Column(name = "g01_city", length = 20, nullable = false)
    private String city;
    @Column(name = "g01_state", length = 20, nullable = false)
    private String state;
    @Column(name = "g01_zipcode", length = 4, nullable = false)
    private String zipCode;
    @Column(name = "g01_country", length = 15, nullable = false)
    private String country;
}
