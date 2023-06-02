package com.ungspp1.gadminbackend.api.modify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ungspp1.gadminbackend.api.modify.to.ChangePassRequestTO;
import com.ungspp1.gadminbackend.api.modify.to.ModifyRequestTO;
import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.restResponse.BaseBodyResponse;
import com.ungspp1.gadminbackend.restResponse.ResponseHelper;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/user")
public class ModifyController 
{

    @Autowired
    private ModifyFacade facade;
    
    @PutMapping(value = "/profile", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> updateUser(@RequestBody ModifyRequestTO request){
        try{
            return ResponseHelper.simpleResponse(facade.updateUserData(request));
        } catch (EngineException e) {
            return ResponseHelper.errorResponse(e.getStatus(), e.getMessage());
        } catch (Exception ex) {
            return ResponseHelper.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());
        }
    }

    @PutMapping(value = "/security" ,produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> changePassword(@RequestBody ChangePassRequestTO request){
        
        try{
            return ResponseHelper.simpleResponse(facade.updateUserPassword(request));
        } catch (EngineException e) {
            return ResponseHelper.errorResponse(e.getStatus(), e.getMessage());
        } catch (Exception ex) {
            return ResponseHelper.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());
        }
    }
}
