package com.ungspp1.gadminbackend.api.branch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ungspp1.gadminbackend.external.techArea.feign.TechAreaFeignClient;
import com.ungspp1.gadminbackend.external.techArea.to.WorkshopTO;

@Service
public class BranchService {

    @Autowired
    private TechAreaFeignClient techAreaFeignClient;

    public List<WorkshopTO> getWorkshops(){
        return techAreaFeignClient.getWorkshops();
    }
}
