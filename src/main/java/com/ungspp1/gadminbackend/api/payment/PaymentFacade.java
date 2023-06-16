package com.ungspp1.gadminbackend.api.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.external.adminArea.feign.AdminAreaFeignClient;
import com.ungspp1.gadminbackend.external.adminArea.to.DebitPaymentTO;
import com.ungspp1.gadminbackend.external.commerceArea.feign.CommerceAreaFeignClient;
import com.ungspp1.gadminbackend.external.commerceArea.to.ClientTO;
import com.ungspp1.gadminbackend.model.entity.VehicleDE;

@Component
public class PaymentFacade {
    
    @Autowired
    private AdminAreaFeignClient adminAreaFeignClient;

    @Autowired
    private CommerceAreaFeignClient commerceAreaFeignClient;

    public void sendDebitPayment(VehicleDE vehicle) throws EngineException {
        
        String dni = vehicle.getDni();
        
        ClientTO client = commerceAreaFeignClient.getClient(dni);

        if (client == null)
            throw new EngineException("No se encontro un cliente con el dni: " + dni, HttpStatus.NO_CONTENT);
    
        String code = generateCode(dni);
        String concept = "P-" + vehicle.getId();
        Float amount = vehicle.getPurchasePrice();
        String fullName = client.getNombre() + " " + client.getApellido();

        DebitPaymentTO payment = new DebitPaymentTO();
        payment.setCodigo_unico(code);
        payment.setConcepto(concept);
        payment.setMonto(amount);
        payment.setNombre_completo(fullName);

        adminAreaFeignClient.debitPayment(payment);
    }

    private String generateCode(String dni){
        String code = dni;
        
        while (code.length() < 20)
            code = 0 + code;
        
        code = "22" + code;
        return code;
    }
}
