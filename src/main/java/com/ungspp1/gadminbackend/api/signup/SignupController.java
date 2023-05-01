package com.ungspp1.gadminbackend.api.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ungspp1.gadminbackend.api.signup.to.SignupUserRequestTO;
import com.ungspp1.gadminbackend.enums.UserTypeEnum;
import com.ungspp1.gadminbackend.response.BaseBodyResponse;
import com.ungspp1.gadminbackend.response.ResponseHelper;

@RestController
@RequestMapping("/api/v1/signup")
public class SignupController {
    @Autowired
    private SignupFacade facade;

    @PostMapping(value = "/client", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> saveClient(@RequestBody SignupUserRequestTO request){
        try{
            request.setType(UserTypeEnum.CLIENTE.name());
            return ResponseHelper.simpleResponse(facade.saveUser(request));
        } catch (Exception e) {
            return ResponseHelper.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    //TODO: logica de tipo de usuario interno (Que tipos hay?)
    @PostMapping(value = "/internal", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> saveInternalUser(@RequestBody SignupUserRequestTO request){
        try{
            request.setType(UserTypeEnum.ADMINISTRADOR.name());
            return ResponseHelper.simpleResponse(facade.saveUser(request));
        } catch (Exception e) {
            return ResponseHelper.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }
}
