package com.ungspp1.gadminbackend.entity;

import java.math.BigInteger;

import jakarta.persistence.Entity;

@Entity
public class AddressDE {
    BigInteger id;
    String street;
    String city;
    String state;
    String zipCode;
    String country;
}
