package com.ungspp1.gadminbackend.api.creditAnalisys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ungspp1.gadminbackend.api.creditAnalisys.facade.creditAnalisysFacade;
import com.ungspp1.gadminbackend.api.creditAnalisys.to.CreditRequestTO;
import com.ungspp1.gadminbackend.api.creditAnalisys.to.CreditResponseTO;
import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.restResponse.BaseBodyResponse;
//import com.ungspp1.gadminbackend.creditAnalisys.to.CurrentAcountResponseTO;



@RestController
@RequestMapping("/api")
public class CreditController {


    @Autowired
    creditAnalisysFacade facade;

    @PostMapping("/credit-analysis")
    public ResponseEntity<CreditResponseTO> analyzeCredit(@RequestBody CreditRequestTO creditRequest) throws EngineException {
    
           if ( facade.analizeCredit(creditRequest)!= null) {
            return ResponseEntity.ok(facade.analizeCredit(creditRequest));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
