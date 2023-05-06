package com.ungspp1.gadminbackend.api.modify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ungspp1.gadminbackend.api.modify.to.ModifyRequestTO;
import com.ungspp1.gadminbackend.restResponse.BaseBodyResponse;
import com.ungspp1.gadminbackend.restResponse.ResponseHelper;


@RestController
@RequestMapping("/settings")
public class ModifyController 
{

    @Autowired
    private ModifyFacade facade;
    
    @PutMapping(produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> updateUser(@RequestBody ModifyRequestTO request){
        
        try{
            return ResponseHelper.simpleResponse(facade.updateUserData(request));
        } catch (Exception e) {
            return ResponseHelper.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }
}
