package com.ungspp1.gadminbackend.api.vehicle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ungspp1.gadminbackend.api.vehicle.to.ModelTO;
import com.ungspp1.gadminbackend.model.entity.ModelDE;
import com.ungspp1.gadminbackend.model.entity.VehicleDE;
import com.ungspp1.gadminbackend.model.repository.ModelRepository;
import com.ungspp1.gadminbackend.model.repository.VehicleRepository;

@Service
public class VehicleService {
    
    @Autowired
    private VehicleRepository repository;

    @Autowired
    private ModelRepository modelRepository;

    public VehicleDE save(VehicleDE vehicleDE) {
        return repository.save(vehicleDE);
    }

    public List<VehicleDE> getAllVehicles() {
        return repository.findAll();
    }

    public VehicleDE getByPlate(String plate) {
        return repository.findByPlate(plate).orElse(null);
    }

    public List<VehicleDE> getByStatus(String status) {
        return repository.findByStatus(status);
    }

    public ModelDE saveModelDE(ModelDE model){
        return modelRepository.save(model);
    }

    public ModelDE getModel(ModelTO modelData){
        return modelRepository.findModel(modelData.getBrand(), modelData.getModel(), modelData.getYear(), modelData.getEngine(), modelData.getFuelType());
    }

    public List<ModelDE> getAllModels(){
        return modelRepository.findAll();
    }
}