package com.ungspp1.gadminbackend.external.techArea.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.ungspp1.gadminbackend.external.techArea.to.WorkshopTO;

@FeignClient(name = "techArea", url = "${techArea.host}")
public interface TechAreaFeignClient {
    
    @GetMapping("/talleres_admin")
    public List<WorkshopTO> getWorkshops();
}
