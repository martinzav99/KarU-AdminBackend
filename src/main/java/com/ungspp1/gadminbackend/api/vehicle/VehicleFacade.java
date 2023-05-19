package com.ungspp1.gadminbackend.api.vehicle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ungspp1.gadminbackend.api.vehicle.mapper.VehicleMapper;
import com.ungspp1.gadminbackend.api.vehicle.to.ModelTO;
import com.ungspp1.gadminbackend.api.vehicle.to.PaperworkTO;
import com.ungspp1.gadminbackend.api.vehicle.to.VehicleRequestTO;
import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.model.entity.ModelDE;
import com.ungspp1.gadminbackend.model.entity.PaperworkDE;

@Component
public class VehicleFacade {

    @Autowired
    private VehicleService service;
    @Autowired 
    private VehicleMapper mapper;

    public Object saveVehicle(VehicleRequestTO request) throws EngineException {
        ModelDE de = service.findModel(request.getModelData());
        PaperworkTO paperworkTO = PaperworkTO.builder().build();
        PaperworkDE paperworkDE = mapper.requestPaperworkToDE(paperworkTO);
        if (de != null){
            return service.save(mapper.requestToDEWithModel (request , de , paperworkDE));
        }else {
             throw new EngineException("El modelo del vehiculo es inexistente.");
        }
    }


    public List<VehicleRequestTO> getAllVehicles(){
        return service.getAllVehicles();
    }


    public Object getByPlate(String plate) {
        return mapper.vehicleRequestToDE(service.getByPlate(plate));
    } 




}
    