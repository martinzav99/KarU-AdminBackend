package com.ungspp1.gadminbackend.api.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ungspp1.gadminbackend.api.login.to.LoginRequestTO;
import com.ungspp1.gadminbackend.api.login.to.LoginResponseTO;

@Component
public class LoginFacade {
    
    @Autowired
    private LoginService loginService;

    public LoginResponseTO getUserByUsername(String username){
        return loginService.getUserByUsername(username);
    }

    public LoginResponseTO getUserByUsernameAndPassword(LoginRequestTO request){
        return loginService.getUserByUsernameAndPassword(request);
    }
}
