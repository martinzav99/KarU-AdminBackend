package com.ungspp1.gadminbackend.creditAnalisys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ungspp1.gadminbackend.creditAnalisys.to.CurrentAcountRequestTO;
import com.ungspp1.gadminbackend.creditAnalisys.to.CurrentAcountResponseTO;
import com.ungspp1.gadminbackend.service.CreditAnalysisService;


@RestController
@RequestMapping("/api")
public class CreditController {

    @Autowired
    CreditAnalysisService creditService;

    @PostMapping("/credit-analysis")
    public ResponseEntity<Double> analyzeCredit(@RequestBody CurrentAcountRequestTO creditRequest) {
         creditRequest = creditService.analyzeCredit(
            creditRequest.getIncomeBalance(),
            creditRequest.getExitBalance()
        );

        if (creditRequest != null) {
            return ResponseEntity.ok(creditRequest.getExitBalance());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
