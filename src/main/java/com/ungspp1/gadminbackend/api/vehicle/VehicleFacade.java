package com.ungspp1.gadminbackend.api.vehicle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.ungspp1.gadminbackend.api.vehicle.mapper.VehicleMapper;
import com.ungspp1.gadminbackend.api.vehicle.to.ModelTO;
import com.ungspp1.gadminbackend.api.vehicle.to.PaperworkTO;
import com.ungspp1.gadminbackend.api.vehicle.to.VehicleTO;
import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.model.entity.ModelDE;
import com.ungspp1.gadminbackend.model.entity.PaperworkDE;
import com.ungspp1.gadminbackend.model.entity.VehicleDE;
import com.ungspp1.gadminbackend.model.enums.VehicleStatusEnum;

@Component
public class VehicleFacade {

    @Autowired
    private VehicleService service;
    @Autowired 
    private VehicleMapper mapper;

    public VehicleDE saveVehicle(VehicleTO request) throws EngineException {
        ModelDE modelDE = service.getModel(request.getModelData());
        PaperworkTO paperworkTO = PaperworkTO.builder().build();
        PaperworkDE paperworkDE = mapper.requestPaperworkToDE(paperworkTO);
        VehicleDE vehicle = service.getByPlate(request.getPlate());
        if (vehicle == null){
            if (modelDE != null){
                VehicleDE newVehicle = mapper.requestToDEWithModel(request , modelDE , paperworkDE);
                newVehicle.setStatus(VehicleStatusEnum.NUEVO.name());
                return service.save(newVehicle);
            }else {
                throw new EngineException("El modelo del vehiculo es inexistente.", HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new EngineException("Ya existe un vehiculo con patente " + request.getPlate(), HttpStatus.BAD_REQUEST);
        }
    }

    public ModelDE saveModel(ModelTO request) throws EngineException {
        ModelDE model = service.getModel(request);
        if(model != null){
            throw new EngineException("El modelo ya esta registrado", HttpStatus.BAD_REQUEST);
        }
        if(request.getBasePrice() == null || request.getBrand() == null || request.getModel() == null || request.getModel() == null){
            throw new EngineException("No se puede guardar un modelo con datos nulos", HttpStatus.BAD_REQUEST);
        }
        ModelDE savedModel = mapper.requestModelToDE(request);
        return service.saveModelDE(savedModel);
    }

    public List<VehicleTO> getAllVehicles(){
        return service.getAllVehicles();
    }

    public VehicleTO getByPlate(String plate) throws EngineException {
        VehicleDE vehicle = service.getByPlate(plate);
        if (vehicle == null){
            throw new EngineException("No existe vehiculo con patente "+plate, HttpStatus.BAD_REQUEST);
        } else {
            return mapper.vehicleRequestToDE(vehicle);
        }
    } 

    public List<ModelTO> getAllModels(){
        return mapper.modelDEsToTOs(service.getAllModels());
    }
    
    public String savePaperwork(PaperworkTO request) throws EngineException{
        VehicleDE vehicle = service.getByPlate(request.getPlate());

        if (vehicle != null){
            if(request.getDebt() != null)
                vehicle.getPaperworkData().setDebt(request.getDebt());
            if(request.getInfractions() != null)
                vehicle.getPaperworkData().setInfractions(request.getInfractions());
            if(request.getRva() != null)
                vehicle.getPaperworkData().setRva(request.getRva());
            if(request.getVpa() != null)
                vehicle.getPaperworkData().setVpa(request.getVpa());
            if(request.getVtv() != null)
                vehicle.getPaperworkData().setVtv(request.getVtv());

            vehicle.setStatus(VehicleStatusEnum.REVISION_LEGAL.name());
            service.save(vehicle);
            return "Paperwork saved";
        }
        else{
            throw new EngineException("The vehicle wasn't found", HttpStatus.BAD_REQUEST);
        }
    }

}
    