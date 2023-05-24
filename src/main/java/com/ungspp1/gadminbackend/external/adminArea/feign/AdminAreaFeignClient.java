package com.ungspp1.gadminbackend.external.adminArea.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.ungspp1.gadminbackend.external.adminArea.to.OfficeTO;

@FeignClient(name = "adminArea", url = "${adminArea.host}")
public interface AdminAreaFeignClient {
    
    @GetMapping("/v1/sucursales")
    public List<OfficeTO> getOffices();
}
