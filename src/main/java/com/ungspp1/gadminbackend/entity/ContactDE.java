package com.ungspp1.gadminbackend.entity;

import java.math.BigInteger;

import jakarta.persistence.Entity;

@Entity
public class ContactDE {
    BigInteger id;
    String email;
    String phoneCode;
    String phoneNumber;
}
