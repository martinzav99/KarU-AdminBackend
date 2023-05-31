package com.ungspp1.gadminbackend.api.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ungspp1.gadminbackend.api.vehicle.to.ModelTO;
import com.ungspp1.gadminbackend.api.vehicle.to.PaperworkTO;
import com.ungspp1.gadminbackend.api.vehicle.to.TechInfoTO;
import com.ungspp1.gadminbackend.api.vehicle.to.VehicleTO;
import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.restResponse.BaseBodyResponse;
import com.ungspp1.gadminbackend.restResponse.ResponseHelper;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/vehicle")
public class VehicleController {
    @Autowired
    private VehicleFacade facade;

    @PostMapping(value = "/saveVehicle", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> saveVehicle(@RequestBody VehicleTO request){
        try{
            return ResponseHelper.simpleResponse(facade.saveVehicle(request));
        } catch (EngineException e) {
            return ResponseHelper.errorResponse(e.getStatus(), e.getMessage());
        } catch (Exception ex) {
            return ResponseHelper.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());
        }
    }
    
    @GetMapping(value = "/getByPlate/{plate}", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> getUserByUsername(@PathVariable String plate){
        try{
            return ResponseHelper.simpleResponse(facade.getByPlate(plate));
        } catch (EngineException e) {
            return ResponseHelper.errorResponse(e.getStatus(), e.getMessage());
        } catch (Exception ex) {
            return ResponseHelper.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());
        }
    }

    @GetMapping(value = "/getAll", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> getAllVehicles(){
        try{
            return ResponseHelper.simpleResponse(facade.getAllVehicles());
        } catch (Exception ex) {
            return ResponseHelper.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());
        }
    }

    @PostMapping(value = "/saveModel", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> saveModel(@RequestBody ModelTO request){
        try{
            return ResponseHelper.simpleResponse(facade.saveModel(request));
        } catch (EngineException e) {
            return ResponseHelper.errorResponse(e.getStatus(), e.getMessage());
        } catch (Exception ex) {
            return ResponseHelper.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());
        }
    }

    @GetMapping(value = "/getAllModels", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> getAllModels(){
        try{
            return ResponseHelper.simpleResponse(facade.getAllModels());
        } catch (Exception ex) {
            return ResponseHelper.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());
        }
    }

    @PostMapping(value = "/savePaperwork", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> savePaperwork(@RequestBody PaperworkTO request){
        try{
            return ResponseHelper.simpleResponse(facade.savePaperwork(request));
        } catch (EngineException e) {
            return ResponseHelper.errorResponse(e.getStatus(), e.getMessage());
        } catch (Exception ex) {
            return ResponseHelper.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());
        }
    }

    @PostMapping(value = "/saveTechInfo", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> saveTechInfo(@RequestBody TechInfoTO request){
        try{
            return ResponseHelper.simpleResponse(facade.saveTechInfo(request));
        } catch (EngineException e) {
            return ResponseHelper.errorResponse(e.getStatus(), e.getMessage());
        } catch (Exception ex) {
            return ResponseHelper.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());
        }
    }

    @GetMapping(value = "/getByStatus/{status}", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> getByStatus(@PathVariable String status){
        try{
            return ResponseHelper.simpleResponse(facade.getByStatus(status));
        } catch (EngineException e) {
            return ResponseHelper.errorResponse(e.getStatus(), e.getMessage());
        } catch (Exception ex) {
            return ResponseHelper.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());
        }
    }
    
}