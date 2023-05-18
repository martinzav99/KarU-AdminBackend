package com.ungspp1.gadminbackend.api.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ungspp1.gadminbackend.api.signup.to.SignupUserRequestTO;
import com.ungspp1.gadminbackend.restResponse.BaseBodyResponse;
import com.ungspp1.gadminbackend.restResponse.ResponseHelper;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/signup")
public class SignupController {
    @Autowired
    private SignupFacade facade;
    
    //NO USADO
    @PostMapping(value = "/client", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> saveClient(@RequestBody SignupUserRequestTO request){
        try{
            facade.normalizeClientUser(request);
            return ResponseHelper.simpleResponse(facade.saveUser(request));
        } catch (Exception e) {
            return ResponseHelper.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    @PostMapping(value = "/internal", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> saveInternalUser(@RequestBody SignupUserRequestTO request){
        try{
            facade.validateInternalUser(request);
            return ResponseHelper.simpleResponse(facade.saveUser(request));
        } catch (Exception e) {
            return ResponseHelper.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }
}
