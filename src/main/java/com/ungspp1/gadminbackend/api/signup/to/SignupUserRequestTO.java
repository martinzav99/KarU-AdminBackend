package com.ungspp1.gadminbackend.api.signup.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupUserRequestTO {
    //User information
    private String fullName;
    private String document;
    private String username;
    private String password;
    private String type;
    private String branch;
    private String technicalLevel;

    //Contact information
    private String email;
    private String phoneCode;
    private String phoneNumber;

    //Address information
    private String street;
    private String streetNumber;
    private String city;
    private String state;
    private String zipCode;
    private String country;

}
