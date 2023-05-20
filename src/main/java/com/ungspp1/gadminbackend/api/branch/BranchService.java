package com.ungspp1.gadminbackend.api.branch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ungspp1.gadminbackend.external.adminArea.feign.AdminAreaFeignClient;
import com.ungspp1.gadminbackend.external.adminArea.to.OfficeTO;
import com.ungspp1.gadminbackend.external.techArea.feign.TechAreaFeignClient;
import com.ungspp1.gadminbackend.external.techArea.to.WorkshopTO;

@Service
public class BranchService {

    @Autowired
    private TechAreaFeignClient techAreaFeignClient;

    @Autowired
    private AdminAreaFeignClient adminAreaFeignClient;

    public List<WorkshopTO> getWorkshops(){
        return techAreaFeignClient.getWorkshops();
    }

    public List<OfficeTO> getOffices(){
        return adminAreaFeignClient.getOffices();
    }
}
