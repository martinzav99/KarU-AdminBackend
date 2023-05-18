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

    public LoginResponseTO loginUser(LoginRequestTO request){
        return loginService.loginUser(request);
    }

    public LoginResponseTO validateAuthCode(LoginRequestTO request){
        return loginService.validateAuthCode(request);
    }
}
