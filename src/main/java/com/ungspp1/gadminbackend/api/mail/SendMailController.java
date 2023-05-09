package com.ungspp1.gadminbackend.api.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ungspp1.gadminbackend.restResponse.BaseBodyResponse;
import com.ungspp1.gadminbackend.restResponse.ResponseHelper;


@RestController
@RequestMapping("/api/v1/login")
public class SendMailController {
    
    @Autowired
    SendMailFacade facade;

    
    // @PostMapping(value = "/sendConfirmationMail", produces = {"application/json"})
    // public ResponseEntity<BaseBodyResponse<?>> getSession(){
    //     try{
    //         return ResponseHelper.simpleResponse(facade.sendAutentcathionMail());
    //     } catch (Exception e) {
    //         return ResponseHelper.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    //     }
    // }
}
