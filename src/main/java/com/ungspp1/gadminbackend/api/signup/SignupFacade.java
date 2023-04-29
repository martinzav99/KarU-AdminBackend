package com.ungspp1.gadminbackend.api.signup;

import org.springframework.stereotype.Component;

import com.ungspp1.gadminbackend.api.signup.to.UserRequestTO;
import com.ungspp1.gadminbackend.api.signup.to.UserResponseTO;

@Component
public class SignupFacade {
    
    public UserResponseTO saveUser(UserRequestTO request){
        return new UserResponseTO();
    }
}
