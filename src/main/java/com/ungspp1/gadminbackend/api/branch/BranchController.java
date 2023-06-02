package com.ungspp1.gadminbackend.api.branch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.restResponse.BaseBodyResponse;
import com.ungspp1.gadminbackend.restResponse.ResponseHelper;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/branch")
public class BranchController {
    
    @Autowired
    private BranchFacade facade;

    @GetMapping(value = "/getOffices", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> startLogin(){
        try{
            return ResponseHelper.simpleResponse(facade.getOffices());
        } catch (EngineException e) {
            return ResponseHelper.errorResponse(e.getStatus(), e.getMessage());
        } catch (Exception ex) {
            return ResponseHelper.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());
        }
    }

    @GetMapping(value = "/getWorkshops", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> sendTwoFactor(){
        try{
            return ResponseHelper.simpleResponse(facade.getWorkshops());
        } catch (EngineException e) {
            return ResponseHelper.errorResponse(e.getStatus(), e.getMessage());
        } catch (Exception ex) {
            return ResponseHelper.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());
        }
    }
}
