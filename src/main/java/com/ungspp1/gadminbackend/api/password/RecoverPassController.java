package com.ungspp1.gadminbackend.api.password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ungspp1.gadminbackend.api.password.to.NewPassRequest;
import com.ungspp1.gadminbackend.api.password.to.RecoverPassRequestTO;
import com.ungspp1.gadminbackend.restResponse.BaseBodyResponse;
import com.ungspp1.gadminbackend.restResponse.ResponseHelper;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/resetPassword")
public class RecoverPassController {
    
    @Autowired 
    RecoverPassFacade facade;

    @PostMapping(produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> rpassword(@RequestBody RecoverPassRequestTO request){
        try{
            return ResponseHelper.simpleResponse(facade.resetPassword(request));
        } catch (Exception e) {
            return ResponseHelper.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    @GetMapping(value = "/{token}", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> vToken(@PathVariable("token") String token){
        try {
            return ResponseHelper.simpleResponse(facade.verifyToken(token));
        } catch (Exception e) {
            return ResponseHelper.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    @PostMapping(value = "/{token}", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> dasasf(@PathVariable("token") String token,@RequestBody NewPassRequest request){
        try {
            return ResponseHelper.simpleResponse(facade.uploadNewPass(token,request));
        } catch (Exception e) {
            return ResponseHelper.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }
    
}
