package com.ungspp1.gadminbackend.api.password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ungspp1.gadminbackend.api.password.to.NewPassRequest;
import com.ungspp1.gadminbackend.api.password.to.RecoverPassRequestTO;
import com.ungspp1.gadminbackend.api.password.to.TokenRequestTO;
import com.ungspp1.gadminbackend.restResponse.BaseBodyResponse;
import com.ungspp1.gadminbackend.restResponse.ResponseHelper;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/resetPassword")
public class RecoverPassController {
    
    @Autowired 
    RecoverPassFacade facade;

    @PostMapping(value = "/validateEmail",produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> vEmail(@RequestBody RecoverPassRequestTO request){
        try{
            return ResponseHelper.simpleResponse(facade.verifyEmail(request));
        } catch (Exception e) {
            return ResponseHelper.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    @PostMapping(value = "/validateToken", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> vToken(@RequestBody TokenRequestTO request){
        try {
            return ResponseHelper.simpleResponse(facade.verifyToken(request));
        } catch (Exception e) {
            return ResponseHelper.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    @PostMapping(value = "/changePassword", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> newPassword(@RequestBody NewPassRequest request){
        try {
            return ResponseHelper.simpleResponse(facade.uploadNewPass(request));
        } catch (Exception e) {
            return ResponseHelper.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }
    
}
