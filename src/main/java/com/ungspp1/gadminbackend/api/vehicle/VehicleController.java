package com.ungspp1.gadminbackend.api.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ungspp1.gadminbackend.api.login.to.LoginRequestTO;
import com.ungspp1.gadminbackend.api.vehicle.to.VehicleRequestTO;
import com.ungspp1.gadminbackend.restResponse.BaseBodyResponse;
import com.ungspp1.gadminbackend.restResponse.ResponseHelper;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/vehicle")
public class VehicleController {
    @Autowired
    VehicleFacade facade;

    @PostMapping(value = "/saveVehicle", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> saveVehicle(@RequestBody VehicleRequestTO request){
        try{
            return ResponseHelper.simpleResponse(facade.saveVehicle(request));
        } catch (Exception e) {
            return ResponseHelper.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    @GetMapping(value = "/getAll", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> getAllVehicles(){
        try{
            return ResponseHelper.simpleResponse(facade.getAllVehicles());
        } catch (Exception e) {
            return ResponseHelper.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }
    
}