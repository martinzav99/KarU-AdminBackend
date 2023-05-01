package com.ungspp1.gadminbackend.api.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ungspp1.gadminbackend.api.login.to.LoginRequestTO;
import com.ungspp1.gadminbackend.response.BaseBodyResponse;
import com.ungspp1.gadminbackend.response.ResponseHelper;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
    
    @Autowired
    private LoginFacade facade;

    @PostMapping(value = "/getSession", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> getSession(@RequestBody LoginRequestTO request){
        try{
            return ResponseHelper.simpleResponse(facade.getUserByUsernameAndPassword(request));
        } catch (Exception e) {
            return ResponseHelper.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

}
