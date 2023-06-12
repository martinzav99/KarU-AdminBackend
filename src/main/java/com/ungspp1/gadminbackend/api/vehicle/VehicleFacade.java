package com.ungspp1.gadminbackend.api.vehicle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.ungspp1.gadminbackend.api.priceHistory.PriceHistoryFacade;
import com.ungspp1.gadminbackend.api.variables.VariablesFacade;
import com.ungspp1.gadminbackend.api.vehicle.mapper.VehicleMapper;
import com.ungspp1.gadminbackend.api.vehicle.to.ModelTO;
import com.ungspp1.gadminbackend.api.vehicle.to.PaperworkTO;
import com.ungspp1.gadminbackend.api.vehicle.to.TechInfoTO;
import com.ungspp1.gadminbackend.api.vehicle.to.UpdateSellPriceTO;
import com.ungspp1.gadminbackend.api.vehicle.to.UpdateStatusTO;
import com.ungspp1.gadminbackend.api.vehicle.to.VehicleResponseTO;
import com.ungspp1.gadminbackend.api.vehicle.to.VehicleTO;
import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.model.entity.ModelDE;
import com.ungspp1.gadminbackend.model.entity.PaperworkDE;
import com.ungspp1.gadminbackend.model.entity.VehicleDE;
import com.ungspp1.gadminbackend.model.enums.HistoryMessageEnum;
import com.ungspp1.gadminbackend.model.enums.VehicleStatusEnum;
import com.ungspp1.gadminbackend.utils.EnumUtils;
import com.ungspp1.gadminbackend.utils.NumberUtils;

@Component
public class VehicleFacade {

    @Autowired
    private VehicleService service;
    @Autowired
    private VariablesFacade variablesFacade;
    @Autowired 
    private VehicleMapper mapper;
    @Autowired
    private PriceHistoryFacade priceHistoryFacade;

    public List<ModelTO> getAllModels(){
        return mapper.modelDEsToTOs(service.getAllModels());
    }

    public VehicleResponseTO saveVehicle(VehicleTO request) throws EngineException {
        ModelDE modelDE = service.getModel(request.getModelData());
        PaperworkTO paperworkTO = PaperworkTO.builder().build();
        PaperworkDE paperworkDE = mapper.requestPaperworkToDE(paperworkTO);
        VehicleDE vehicle = service.getByPlate(request.getPlate());
        
        if (vehicle != null){
            throw new EngineException("Ya existe un vehiculo con patente " + request.getPlate(), HttpStatus.BAD_REQUEST);
        } else if (modelDE == null){
            throw new EngineException("El modelo del vehiculo es inexistente.", HttpStatus.BAD_REQUEST);
        } else if (!validateVehicleFields(request)){
            throw new EngineException("Campos faltantes", HttpStatus.BAD_REQUEST);
        } else if (!EnumUtils.validateOriginEnum(request.getOrigin())) {
            throw new EngineException("El origen debe ser: NACIONAL o IMPORTADO", HttpStatus.BAD_REQUEST);
        } else {
            VehicleDE newVehicle = mapper.requestToDEWithModel(request , modelDE , paperworkDE);
            newVehicle.setStatus(VehicleStatusEnum.ESPERA_REVISION_INICIAL.name());
            return mapper.deToResponseTO(service.save(newVehicle));
        }  
    }

    public VehicleResponseTO saveTechInfo(TechInfoTO request) throws EngineException{
        float minimumScore = variablesFacade.getVariableValue("PUNTAJE_MINIMO");
        if (request.getScore() == null || request.getScore()>100 || request.getScore()<minimumScore){
            throw new EngineException("El puntaje debe estar entre "+minimumScore+" y 100", HttpStatus.BAD_REQUEST);
        }
        if (request.getRepairCost() == null || request.getRepairCost()<0){
            throw new EngineException("El costo de reparacion debe ser mayor o igual a 0", HttpStatus.BAD_REQUEST);
        }
        VehicleDE vehicle = service.getByPlate(request.getPlate());
        if (vehicle.getStatus().equals(VehicleStatusEnum.ESPERA_REVISION_TECNICA.name())){
            mapVehicleTechInfo(request, vehicle);
            VehicleResponseTO response = mapper.deToResponseTO(service.save(vehicle));
            priceHistoryFacade.newVehiclePriceHistory(request.getPlate(), response.getSellPrice(), HistoryMessageEnum.NUEVO_VEHICULO.name());
            return response;
        } else {
            throw new EngineException("El vehiculo no se encuentra en revisión tecnica", HttpStatus.BAD_REQUEST);
        }
    }

    public ModelTO saveModel(ModelTO request) throws EngineException {
        ModelDE model = service.getModel(request);
        if(model != null){
            throw new EngineException("El modelo ya esta registrado", HttpStatus.BAD_REQUEST);
        }
        if(request.getBasePrice() == null || request.getBrand() == null || request.getModel() == null || request.getModel() == null || request.getEngine() == null || request.getFuelType() == null || request.getCategory() == null){
            throw new EngineException("No se puede guardar un modelo con datos nulos", HttpStatus.BAD_REQUEST);
        }
        if(!EnumUtils.validateFuelTypeEnum(request.getFuelType())){
            throw new EngineException("El tipo de combustible no es valido", HttpStatus.BAD_REQUEST);
        }
        if(!EnumUtils.validateVehicleCategoryEnum(request.getCategory())){
            throw new EngineException("La gama del vehiculo no es valida", HttpStatus.BAD_REQUEST);
        }
        ModelDE savedModel = mapper.requestModelToDE(request);
        ModelTO response = mapper.modelDEtoTO(service.saveModelDE(savedModel));
        String modelReference = request.getBrand()+" "+request.getModel()+" "+request.getYear()+" "+request.getFuelType()+" "+request.getEngine();
        priceHistoryFacade.newModelPriceHistory(modelReference, request.getBasePrice(), HistoryMessageEnum.NUEVO_MODELO.name());
        return response;
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

    public String updateStatus(UpdateStatusTO request) throws EngineException {
        VehicleDE vehicle = service.getByPlate(request.getPlate());
        if (vehicle == null){
            throw new EngineException("No se encontró el vehiculo", HttpStatus.BAD_REQUEST);
        } else {
            Boolean found = EnumUtils.validateVehicleStatusEnum(request.getStatus());
            if(found){
                vehicle.setStatus(request.getStatus());
                service.save(vehicle);
                return "Estado actualizado";
            } else {
                throw new EngineException("El estado ingresado no es valido", HttpStatus.BAD_REQUEST);
            }
        }
    }

    public String savePaperwork(PaperworkTO request) throws EngineException{
        VehicleDE vehicle = service.getByPlate(request.getPlate());

        if (vehicle != null){
            float debtPercentage = NumberUtils.toPercentage(variablesFacade.getVariableValue("PORCENTAJE_DEUDA"));
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
            return "Documentos guardados";
        }
        else{
            throw new EngineException("No se encontró el vehiculo", HttpStatus.BAD_REQUEST);
        }
    }

    private void mapVehicleTechInfo(TechInfoTO request, VehicleDE vehicle) throws EngineException {
        vehicle.setMessage(request.getMessage());
        vehicle.setScore(request.getScore());
        vehicle.setRepairCost(request.getRepairCost());
        vehicle.setPurchasePrice(calculatePurchasePrice(vehicle));
        vehicle.setSellPrice(calculateSellPrice(vehicle));
        vehicle.setStatus(VehicleStatusEnum.ESPERA_DECISION_FINAL.name());
    }

    public String updateSellPrice(UpdateSellPriceTO request) throws EngineException{
        
        if (request.getPlate() == null || request.getNewSellPrice() == null)
            throw new EngineException("Ingrese los datos necesarios", HttpStatus.BAD_REQUEST);
        
        VehicleDE vehicle = service.getByPlate(request.getPlate());

        if (vehicle == null)
            throw new EngineException("No se encontró el vehiculo", HttpStatus.BAD_REQUEST);
        
        vehicle.setSellPrice(request.getNewSellPrice());
        service.save(vehicle);
        
        priceHistoryFacade.vehiclePriceChangeHistory(request.getPlate(),HistoryMessageEnum.CAMBIO_VENTA_INDIVIDUAL.name(),request.getNewSellPrice());
        return "Precio de venta actualizado";
    }

    public String updatePriceByModel(ModelTO request) throws EngineException{
        
        if(!validateModelField(request))
            throw new EngineException("Ingrese los datos necesarios", HttpStatus.BAD_REQUEST);

        ModelDE model = service.getModel(request);
        
        if (model == null)
            throw new EngineException("No se encontró el modelo", HttpStatus.BAD_REQUEST);

        model.setBasePrice(request.getBasePrice());
        service.saveModelDE(model);
        
        String modelReference = request.getBrand()+" "+request.getModel()+" "+request.getYear()+" "+request.getFuelType()+" "+request.getEngine();
        priceHistoryFacade.modelPriceChangeHistory(modelReference, HistoryMessageEnum.CAMBIO_MODELO_INDIVIDUAL.name(), request.getBasePrice());
        
        return "Precio base de modelo actualizado";
    }
    
    public String updatePricesByInflation(Float inflation) throws EngineException{

        if (inflation == null)
            throw new EngineException("No hay modelos disponibles", HttpStatus.BAD_REQUEST);
          
        List<ModelDE> models = service.getAllModels();

        if (models.isEmpty())
            throw new EngineException("No hay modelos disponibles", HttpStatus.BAD_REQUEST);
          

        for (ModelDE model : models){
            if (model.getBasePrice() != null){
                model.setBasePrice(model.getBasePrice() + ((model.getBasePrice() * inflation) / 100 ) );
                service.saveModelDE(model);
            }
        }

        List<VehicleDE> vehicles = service.getAllAvailable();

        if (vehicles.isEmpty())
            throw new EngineException("No hay vehiculos disponibles", HttpStatus.BAD_REQUEST);
        
        for (VehicleDE vehicle : vehicles){
            if (vehicle.getSellPrice() != null){
                vehicle.setSellPrice(vehicle.getSellPrice() + ((vehicle.getSellPrice()*inflation)/100));
                service.save(vehicle);
            }
        }

        priceHistoryFacade.saveMassiveChangeHistory("Cambio masivo: precio base (modelo), precio venta (vehiculos)", HistoryMessageEnum.CAMBIO_PRECIO_MASIVO.name(), inflation);
        return "Se actualizaron precios base de modelos y precios de venta";
    }

    //CALCULO TEMPORAL DE PRECIO DE COMPRA
    private Float calculatePurchasePrice(VehicleDE vehicle) throws EngineException{
        Float basePrice = vehicle.getModelData().getBasePrice();
        Float repairCost = vehicle.getRepairCost();
        Float purchasePercentage = NumberUtils.toPercentage(variablesFacade.getVariableValue("PORCENTAJE_COMPRA"));
        Float finalPrice = (float) (basePrice*purchasePercentage);
        finalPrice = finalPrice-repairCost;
        
        if(vehicle.getPaperworkData().getDebt()!=null){
            finalPrice = finalPrice-vehicle.getPaperworkData().getDebt();
            if (finalPrice < 0){
                throw new EngineException("Las deudas son mayores al valor del vehiculo", HttpStatus.BAD_REQUEST);
            }
        }
        return finalPrice;
    }

    //CALCULO TEMPORAL DE PRECIO DE VENTA
    private Float calculateSellPrice(VehicleDE vehicle) throws EngineException{
        Float basePrice = vehicle.getModelData().getBasePrice();
        Float sellPercentage = NumberUtils.toPercentage(variablesFacade.getVariableValue("PORCENTAJE_VENTA"));
        Float sellPrice = (float) (basePrice*sellPercentage);
        return sellPrice;
    }

    private boolean validateVehicleFields(VehicleTO request) {
        if (request.getGnc() != null
        && request.getDni() != null
        && request.getKilometers() != null
        && request.getOrigin() != null
        && request.getPlate() != null){
            return true;
        }
        return false;
    }

    private boolean validateModelField(ModelTO request){
        return request.getBasePrice() != null 
                && request.getBrand() != null 
                && request.getModel() !=null 
                && request.getYear() !=null 
                && request.getEngine() !=null 
                && request.getFuelType() != null;
    }

}
    