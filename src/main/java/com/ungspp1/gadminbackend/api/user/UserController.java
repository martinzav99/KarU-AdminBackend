package com.ungspp1.gadminbackend.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ungspp1.gadminbackend.restResponse.BaseBodyResponse;
import com.ungspp1.gadminbackend.restResponse.ResponseHelper;

import jakarta.websocket.server.PathParam;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserFacade facade;
    
    @GetMapping(value = "/getByUser/{username}", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> getUserByUsername(@PathVariable String username){
        try{
            return ResponseHelper.simpleResponse(facade.getByUsername(username));
        } catch (Exception e) {
            return ResponseHelper.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    @GetMapping(value = "/getByType/{type}", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> getUsersByType(@PathVariable String type){
        try{
            return ResponseHelper.simpleResponse(facade.getByType(type));
        } catch (Exception e) {
            return ResponseHelper.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    @GetMapping(value = "/getByBranch/{branch}", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> getUsersByBranch(@PathVariable String branch){
        try{
            return ResponseHelper.simpleResponse(facade.getByBranch(branch));
        } catch (Exception e) {
            return ResponseHelper.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    @GetMapping(value = "/getByTechLevel/{level}", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> getUsersByTechLevel(@PathVariable String level){
        try{
            return ResponseHelper.simpleResponse(facade.getByTechLevel(level));
        } catch (Exception e) {
            return ResponseHelper.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    @GetMapping(value = "/getAll", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> getAllUsers(){
        try{
            return ResponseHelper.simpleResponse(facade.getAllUsers());
        } catch (Exception e) {
            return ResponseHelper.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }


}
