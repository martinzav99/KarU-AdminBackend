package com.ungspp1.gadminbackend.api.vehicle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ungspp1.gadminbackend.api.vehicle.mapper.VehicleMapper;
import com.ungspp1.gadminbackend.api.vehicle.to.ModelTO;
import com.ungspp1.gadminbackend.api.vehicle.to.VehicleRequestTO;
import com.ungspp1.gadminbackend.model.entity.ModelDE;

@Component
public class VehicleFacade {

    @Autowired
    private VehicleService service;
    @Autowired 
    private VehicleMapper mapper;

    public Object saveVehicle(VehicleRequestTO request) {
        ModelDE de = service.findModel(request.getModelData());
        if (de != null){
            return service.save(mapper.requestToDEWithModel (request , de));
        }else {
            return service.save(mapper.requestToDE (request));
        }
    }


    public List<VehicleRequestTO> getAllVehicles(){
        return service.getAllVehicles();
    }


    public Object getByPlate(String plate) {
        return mapper.vehicleRequestToDE(service.getByPlate(plate));
    } 




}
    