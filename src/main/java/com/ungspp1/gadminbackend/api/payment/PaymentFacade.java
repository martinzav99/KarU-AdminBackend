package com.ungspp1.gadminbackend.api.payment;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;

import com.ungspp1.gadminbackend.external.adminArea.feign.AdminAreaFeignClient;
import com.ungspp1.gadminbackend.external.adminArea.to.DebitPaymentTO;
import com.ungspp1.gadminbackend.model.entity.VehicleDE;

public class PaymentFacade {
    
    @Autowired
    private AdminAreaFeignClient adminAreaFeignClient;

    public void sendDebitPayment(VehicleDE vehicle){
        
        String code = generateCode(vehicle.getId());
        String concept = "P-1";
        Float amount = vehicle.getSellPrice();
        String dni = vehicle.getDni();
        String fullName = "aca hago un get de una persona";

        DebitPaymentTO payment = new DebitPaymentTO(code, amount, concept, fullName, dni);
        adminAreaFeignClient.debitPayment(payment);
    }

    private String generateCode(BigInteger id){
        String code = "" + id;
        
        while (code.length() <= 20)
            code = 0 + code;
        
        code = "22" + code;
        return code;
    }
}
