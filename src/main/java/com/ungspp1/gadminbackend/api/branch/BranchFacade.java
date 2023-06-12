package com.ungspp1.gadminbackend.api.branch;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ungspp1.gadminbackend.api.branch.mapper.BranchMapper;
import com.ungspp1.gadminbackend.api.branch.to.OfficeResponseTO;
import com.ungspp1.gadminbackend.api.branch.to.WorkshopResponseTO;
import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.external.adminArea.to.OfficeTO;
import com.ungspp1.gadminbackend.external.techArea.to.WorkshopTO;

@Service
public class BranchFacade {
    
    @Autowired
    private BranchService branchService;

    @Autowired
    private BranchMapper branchMapper;

    public List<WorkshopResponseTO> getWorkshops() throws EngineException{
        List<WorkshopTO> workshopTOs = branchService.getWorkshops();
        if (workshopTOs.isEmpty()){
            throw new EngineException("No se encontraron talleres", HttpStatus.NO_CONTENT);
        } else {
            return branchMapper.workshopsToResponseList(workshopTOs);
        }
    }

    public List<OfficeResponseTO> getOffices() throws EngineException{
        List<OfficeTO> officeTOs = branchService.getOffices()
                                   .stream()
                                   .filter(office -> office.isActiva())
                                   .collect(Collectors.toList());
        if (officeTOs.isEmpty()){
            throw new EngineException("No se encontraron sucursales", HttpStatus.NO_CONTENT);
        } else {
            return branchMapper.officesToResponseList(officeTOs);
        }
    }
}
