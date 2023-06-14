package com.ungspp1.gadminbackend.api.creditAnalisys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ungspp1.gadminbackend.api.creditAnalisys.to.CreditRequestTO;
import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.restResponse.BaseBodyResponse;
import com.ungspp1.gadminbackend.restResponse.ResponseHelper;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/credit-analysis")
public class CreditController {

    @Autowired
    private CreditAnalisysFacade facade;

    @PostMapping("/generateScoring")
    public ResponseEntity<BaseBodyResponse<?>> analyzeCredit(@RequestBody CreditRequestTO creditRequest) throws EngineException {
        try{
            return ResponseHelper.simpleResponse(facade.generateScore(creditRequest));
        } catch (EngineException e) {
            return ResponseHelper.errorResponse(e.getStatus(), e.getMessage());
        } catch (Exception e) {
            return ResponseHelper.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() , e.getMessage());
        }
    }

    @GetMapping("/getScoring")
    public ResponseEntity<BaseBodyResponse<?>> findByDocumentScoring(@RequestParam String document) throws EngineException {
        try {   
             return ResponseHelper.simpleResponse(facade.findByDocument(document));
        } catch (EngineException e) {
            return ResponseHelper.errorResponse(e.getStatus(), e.getMessage());
        } catch (Exception e) {
            return ResponseHelper.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() , e.getMessage());
        }

    }
}
