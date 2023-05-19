package com.ungspp1.gadminbackend.api.vehicle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ungspp1.gadminbackend.api.vehicle.mapper.VehicleMapper;
import com.ungspp1.gadminbackend.api.vehicle.to.ModelTO;
import com.ungspp1.gadminbackend.api.vehicle.to.PaperworkTO;
import com.ungspp1.gadminbackend.api.vehicle.to.VehicleTO;
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

    public VehicleDE save(VehicleDE vehicleDE) {
        return repository.save(vehicleDE);
    }

    public List<VehicleTO> getAllVehicles() {
        List<VehicleDE> vehicleDEs = repository.findAll();
        return mapper.vehicleDEtoRequestTOList(vehicleDEs);
    }

    public VehicleDE getByPlate(String plate) {
        return repository.findByPlate(plate).orElse(null);
    }

    public ModelDE saveModelDE(ModelDE model){
        return modelRepository.save(model);
    }

    public ModelDE getModel(ModelTO modelData){
        return modelRepository.findByBrandAndModelAndYear(modelData.getBrand(), modelData.getModel(), modelData.getYear());
    }

    public List<ModelDE> getAllModels(){
        return modelRepository.findAll();
    }

    public void updateVehiclePaperwork(PaperworkTO request){
        
    }
}