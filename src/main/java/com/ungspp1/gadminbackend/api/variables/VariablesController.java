package com.ungspp1.gadminbackend.api.variables;

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

import com.ungspp1.gadminbackend.api.variables.to.VariableTO;
import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.restResponse.BaseBodyResponse;
import com.ungspp1.gadminbackend.restResponse.ResponseHelper;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/variables")
public class VariablesController {
    @Autowired
    private VariablesFacade facade;

    @PostMapping(value = "/update", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> updateVariable(@RequestBody VariableTO request){
        try{
            return ResponseHelper.simpleResponse(facade.updateVariableValue(request));
        } catch (EngineException e) {
            return ResponseHelper.errorResponse(e.getStatus(), e.getMessage());
        } catch (Exception ex) {
            return ResponseHelper.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());
        }
    }
    
    @GetMapping(value = "/getValue/{name}", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> getVariableValue(@PathVariable String name){
        try{
            return ResponseHelper.simpleResponse(facade.getVariableValue(name));
        } catch (EngineException e) {
            return ResponseHelper.errorResponse(e.getStatus(), e.getMessage());
        } catch (Exception ex) {
            return ResponseHelper.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());
        }
    }

    @GetMapping(value = "/getAll", produces = {"application/json"})
    public ResponseEntity<BaseBodyResponse<?>> getAllVariables(){
        try{
            return ResponseHelper.simpleResponse(facade.getAllVariables());
        } catch (EngineException e) {
            return ResponseHelper.errorResponse(e.getStatus(), e.getMessage());
        } catch (Exception ex) {
            return ResponseHelper.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());
        }
    }

    

}