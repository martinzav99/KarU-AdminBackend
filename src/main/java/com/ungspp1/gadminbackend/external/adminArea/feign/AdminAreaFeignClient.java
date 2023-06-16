package com.ungspp1.gadminbackend.external.adminArea.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ungspp1.gadminbackend.external.adminArea.to.DebitPaymentTO;
import com.ungspp1.gadminbackend.external.adminArea.to.OfficeTO;

@FeignClient(name = "adminArea", url = "${adminArea.host}")
public interface AdminAreaFeignClient {
    
    @GetMapping("/v1/sucursales")
    public List<OfficeTO> getOffices();

    @PostMapping("/v1/movimiento")
    public HttpStatus debitPayment(@RequestBody DebitPaymentTO request);
}
