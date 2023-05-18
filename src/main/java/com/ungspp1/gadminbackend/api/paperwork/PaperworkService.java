package com.ungspp1.gadminbackend.api.paperwork;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ungspp1.gadminbackend.api.paperwork.to.PaperworkRequestTO;
import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.model.entity.VehicleDE;
import com.ungspp1.gadminbackend.model.repository.VehicleRepository;

@Service
public class PaperworkService {
    
    @Autowired
    private VehicleRepository vehicleRepository;
    
    public HttpStatus updateVehiclePaperwork(PaperworkRequestTO request) throws EngineException{
        Optional<VehicleDE> vechicleOptional = vehicleRepository.findByPlate(request.getPlate());

        if (vechicleOptional.isPresent()){
            VehicleDE vehicle = vechicleOptional.get();
            if(request.getDebt() != null)
                vehicle.getPaperworkData().setDebt(request.getDebt());
            if(request.getCedula() != null)
                vehicle.getPaperworkData().setCedula(request.getCedula());
            if(request.getHistorical() != null)
                vehicle.getPaperworkData().setHistorical(request.getHistorical());
            if(request.getInfractions() != null)
                vehicle.getPaperworkData().setInfractions(request.getInfractions());
            if(request.getRva() != null)
                vehicle.getPaperworkData().setRva(request.getRva());
            if(request.getVpa() != null)
                vehicle.getPaperworkData().setVpa(request.getVpa());
            if(request.getVtv() != null)
                vehicle.getPaperworkData().setVtv(request.getVtv());

            return HttpStatus.OK;
        }
        else{
            throw new EngineException("The vehicle wasn't found", HttpStatus.BAD_REQUEST);
        }
    }
     
}
