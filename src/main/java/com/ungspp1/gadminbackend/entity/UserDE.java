package com.ungspp1.gadminbackend.entity;

import java.math.BigInteger;

import jakarta.persistence.Entity;

@Entity
public class UserDE {
    BigInteger id;
    String fullName;
    String document;
    String userName;
    String password;
    String type;
    String status;
    String branch;
    //String billingId;
    ContactDE contactData;
    AddressDE addressData;
}
