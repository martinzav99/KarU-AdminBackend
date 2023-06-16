package com.ungspp1.gadminbackend.external.commerceArea.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ungspp1.gadminbackend.external.commerceArea.to.ClientTO;

@FeignClient(name = "commerceArea", url = "${commerceArea.host}")
public interface CommerceAreaFeignClient {
    
    @GetMapping("/api-gc/clientes/cliente")
    public ClientTO getClient(@RequestParam("dni") String dni);
}
