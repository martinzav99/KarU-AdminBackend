package com.ungspp1.gadminbackend.api.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ungspp1.gadminbackend.api.signup.to.UserRequestTO;
import com.ungspp1.gadminbackend.response.BaseBodyResponse;
import com.ungspp1.gadminbackend.response.ResponseHelper;

@RestController
@RequestMapping("/api/v1/signup")
public class SignupController {
    @Autowired
    private SignupFacade facade;

    @PostMapping(value = "/saveUser", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> saveUser(@RequestBody UserRequestTO request){
        try{
            return ResponseHelper.simpleResponse(facade.saveUser(request));
        } catch (Exception e) {
            return ResponseHelper.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }
}
