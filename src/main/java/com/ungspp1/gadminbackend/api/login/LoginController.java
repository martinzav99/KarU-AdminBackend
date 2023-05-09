package com.ungspp1.gadminbackend.api.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ungspp1.gadminbackend.api.login.to.LoginRequestTO;
import com.ungspp1.gadminbackend.restResponse.BaseBodyResponse;
import com.ungspp1.gadminbackend.restResponse.ResponseHelper;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/login")
public class LoginController {
    
    @Autowired
    private LoginFacade facade;

    @PostMapping(value = "/startLogin", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> startLogin(@RequestBody LoginRequestTO request){
        try{
            return ResponseHelper.simpleResponse(facade.loginUser(request));
        } catch (Exception e) {
            return ResponseHelper.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    @PostMapping(value = "/twoFactorAuth", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> sendTwoFactor(@RequestBody LoginRequestTO request){
        try{
            return ResponseHelper.simpleResponse(facade.validateAuthCode(request));
        } catch (Exception e) {
            return ResponseHelper.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

}
