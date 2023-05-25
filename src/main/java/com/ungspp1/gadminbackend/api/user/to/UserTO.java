package com.ungspp1.gadminbackend.api.user.to;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTO {
    private BigInteger id;
    private String fullName;
    private String document;
    private String username;
    private String type;
    private String branch;
    private String technicalLevel;
    private String email;
    private String phoneCode;
    private String phoneNumber;
    private String street;
    private String streetNumber;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
