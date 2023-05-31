package com.ungspp1.gadminbackend.api.vehicle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.ungspp1.gadminbackend.api.utils.NumberUtils;
import com.ungspp1.gadminbackend.api.vehicle.mapper.VehicleMapper;
import com.ungspp1.gadminbackend.api.vehicle.to.ModelTO;
import com.ungspp1.gadminbackend.api.vehicle.to.PaperworkTO;
import com.ungspp1.gadminbackend.api.vehicle.to.TechInfoTO;
import com.ungspp1.gadminbackend.api.vehicle.to.VehicleResponseTO;
import com.ungspp1.gadminbackend.api.vehicle.to.VehicleTO;
import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.model.entity.ModelDE;
import com.ungspp1.gadminbackend.model.entity.PaperworkDE;
import com.ungspp1.gadminbackend.model.entity.VehicleDE;
import com.ungspp1.gadminbackend.model.enums.VehicleStatusEnum;
import com.ungspp1.gadminbackend.service.VariablesService;

@Component
public class VehicleFacade {

    @Autowired
    private VehicleService service;
    @Autowired
    private VariablesService variablesService;
    @Autowired 
    private VehicleMapper mapper;

    public VehicleResponseTO saveVehicle(VehicleTO request) throws EngineException {
        ModelDE modelDE = service.getModel(request.getModelData());
        PaperworkTO paperworkTO = PaperworkTO.builder().build();
        PaperworkDE paperworkDE = mapper.requestPaperworkToDE(paperworkTO);
        VehicleDE vehicle = service.getByPlate(request.getPlate());
        if (vehicle == null){
            if (modelDE != null){
                VehicleDE newVehicle = mapper.requestToDEWithModel(request , modelDE , paperworkDE);
                newVehicle.setStatus(VehicleStatusEnum.ESPERA_REVISION_INICIAL.name());
                return mapper.deToResponseTO(service.save(newVehicle));
            }else {
                throw new EngineException("El modelo del vehiculo es inexistente.", HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new EngineException("Ya existe un vehiculo con patente " + request.getPlate(), HttpStatus.BAD_REQUEST);
        }
    }

    public VehicleResponseTO saveTechInfo(TechInfoTO request) throws EngineException{
        float minimumScore = variablesService.getVariable("PUNTAJE_MINIMO");
        if (request.getScore()>100 || request.getScore()<minimumScore){
            throw new EngineException("El puntaje debe estar entre "+minimumScore+" y 100", HttpStatus.BAD_REQUEST);
        }
        VehicleDE vehicle = service.getByPlate(request.getPlate());
        if (vehicle.getStatus().equals(VehicleStatusEnum.ESPERA_REVISION_TECNICA.name())){
            vehicle.setMessage(request.getMessage());
            vehicle.setScore(request.getScore());
            vehicle.setPurchasePrice(calculatePurchasePrice(vehicle));
            vehicle.setSellPrice(calculateSellPrice(vehicle));
            vehicle.setStatus(VehicleStatusEnum.ESPERA_DECISION_FINAL.name());
            return mapper.deToResponseTO(service.save(vehicle));
        } else {
            throw new EngineException("El vehiculo no se encuentra en revisión tecnica", HttpStatus.BAD_REQUEST);
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

    public List<VehicleResponseTO> getAllVehicles(){
        return mapper.deListToResponseList(service.getAllVehicles());
    }

    public VehicleResponseTO getByPlate(String plate) throws EngineException {
        VehicleDE vehicle = service.getByPlate(plate);
        if (vehicle == null){
            throw new EngineException("No existe vehiculo con patente "+plate, HttpStatus.BAD_REQUEST);
        } else {
            return mapper.deToResponseTO(vehicle);
        }
    } 

    public List<VehicleResponseTO> getByStatus(String status) throws EngineException {
        List<VehicleDE> vehicles = service.getByStatus(status);
        if (vehicles.isEmpty()){
            throw new EngineException("No se encontraron vehiculos con estado "+status, HttpStatus.BAD_REQUEST);
        } else {
            return mapper.deListToResponseList(vehicles);
        }
    } 

    public List<ModelTO> getAllModels(){
        return mapper.modelDEsToTOs(service.getAllModels());
    }
    
    public String savePaperwork(PaperworkTO request) throws EngineException{
        VehicleDE vehicle = service.getByPlate(request.getPlate());

        if (vehicle != null){
            float debtPercentage = NumberUtils.toPercentage(variablesService.getVariable("PORCENTAJE_DEUDA"));
            float modelPrice = vehicle.getModelData().getBasePrice();
            float debtLimit = (float) (modelPrice*debtPercentage);
            if(request.getDebt()>debtLimit){
                throw new EngineException("No se admiten vehiculos con deuda mayor al "+debtPercentage*100+"% del valor del modelo("+modelPrice+")" , HttpStatus.BAD_REQUEST);
            } else {
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
            }
            vehicle.setStatus(VehicleStatusEnum.ESPERA_REVISION_TECNICA.name());
            service.save(vehicle);
            return "Paperwork saved";
        }
        else{
            throw new EngineException("The vehicle wasn't found", HttpStatus.BAD_REQUEST);
        }
    }

    //CALCULO TEMPORAL DE PRECIO DE COMPRA
    private float calculatePurchasePrice(VehicleDE vehicle) throws EngineException{
        float basePrice = vehicle.getModelData().getBasePrice();
        float vehicleScore = NumberUtils.toPercentage(vehicle.getScore());
        float purchasePercentage = NumberUtils.toPercentage(variablesService.getVariable("PORCENTAJE_COMPRA"));
        float finalPrice = (float) (basePrice*purchasePercentage);
        finalPrice = finalPrice*vehicleScore;
        
        if(vehicle.getPaperworkData().getDebt()!=null){
            finalPrice = finalPrice-vehicle.getPaperworkData().getDebt();
            if (finalPrice < 0){
                throw new EngineException("Las deudas son mayores al valor del vehiculo", HttpStatus.BAD_REQUEST);
            }
        }
        return finalPrice;
    }

    //CALCULO TEMPORAL DE PRECIO DE VENTA
    private float calculateSellPrice(VehicleDE vehicle) throws EngineException{
        float purchasePrice = vehicle.getPurchasePrice();
        float sellPercentage = NumberUtils.toPercentage(variablesService.getVariable("PORCENTAJE_VENTA"));
        float sellPrice = (float) (purchasePrice*sellPercentage);
        return sellPrice;
    }

}
    