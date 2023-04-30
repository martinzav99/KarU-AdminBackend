package com.ungspp1.gadminbackend.api.signup;

import com.ungspp1.gadminbackend.exceptions.EngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ungspp1.gadminbackend.api.signup.to.SignupUserRequestTO;
import com.ungspp1.gadminbackend.api.signup.to.SignupUserResponseTO;

@Component
public class SignupFacade {

    @Autowired
    private SignupService signupService;
    
    public SignupUserResponseTO saveUser(SignupUserRequestTO request) throws EngineException {
        return signupService.saveUser(request);
    }
}
