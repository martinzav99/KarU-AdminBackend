package com.ungspp1.gadminbackend.api.signup.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestTO {
    //User information
    String fullName;
    String document;
    String userName;
    String password;
    String type;
    String status;
    String branch;

    //Contact information
    String email;
    String phoneCode;
    String phoneNumber;

    //Address information
    String street;
    String city;
    String state;
    String zipCode;
    String country;

}
