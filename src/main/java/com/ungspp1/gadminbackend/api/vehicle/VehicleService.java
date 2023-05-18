package com.ungspp1.gadminbackend.api.vehicle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ungspp1.gadminbackend.api.vehicle.mapper.VehicleMapper;
import com.ungspp1.gadminbackend.api.vehicle.to.ModelTO;
import com.ungspp1.gadminbackend.api.vehicle.to.VehicleRequestTO;
import com.ungspp1.gadminbackend.model.entity.ModelDE;
import com.ungspp1.gadminbackend.model.entity.VehicleDE;
import com.ungspp1.gadminbackend.model.repository.ModelRepository;
import com.ungspp1.gadminbackend.model.repository.VehicleRepository;

@Service
public class VehicleService {
    
    @Autowired
    private VehicleRepository repository;

    @Autowired
    private VehicleMapper mapper;

    @Autowired
    private ModelRepository modelRepository;

    public Object save(VehicleDE vehicleDE) {
        return repository.save(vehicleDE);
    }

    public List<VehicleRequestTO> getAllVehicles() {
        List<VehicleDE> vehicleDEs = repository.findAll();
        return mapper.vehicleDEtoRequestTOList(vehicleDEs);
    }

    public  ModelDE findModel(ModelTO modelData) {
        return modelRepository.findByModelAndYear(modelData.getModel(), modelData.getYear());
    }

    public VehicleDE getByPlate(String plate) {
        return repository.findByPlate(plate).get();
    }


}