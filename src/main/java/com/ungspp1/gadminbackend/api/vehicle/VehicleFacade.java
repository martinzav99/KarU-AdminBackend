package com.ungspp1.gadminbackend.api.vehicle;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.ungspp1.gadminbackend.api.payment.PaymentFacade;
import com.ungspp1.gadminbackend.api.priceHistory.PriceHistoryFacade;
import com.ungspp1.gadminbackend.api.variables.VariablesFacade;
import com.ungspp1.gadminbackend.api.vehicle.mapper.VehicleMapper;
import com.ungspp1.gadminbackend.api.vehicle.to.EnableVehicleTO;
import com.ungspp1.gadminbackend.api.vehicle.to.ModelTO;
import com.ungspp1.gadminbackend.api.vehicle.to.PaperworkTO;
import com.ungspp1.gadminbackend.api.vehicle.to.TechInfoTO;
import com.ungspp1.gadminbackend.api.vehicle.to.UpdateDniTO;
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
    @Autowired
    private PaymentFacade paymentFacade;

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
        }else if (!validatePlate(request.getPlate())) {
            throw new EngineException("La matricula no sigue un patron valido", HttpStatus.BAD_REQUEST);
        } else {
            VehicleDE newVehicle = mapper.requestToDEWithModel(request , modelDE , paperworkDE);
            newVehicle.setStatus(VehicleStatusEnum.ESPERA_REVISION_LEGAL.name());
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

    public String updateFinalStatus(UpdateStatusTO request) throws EngineException {
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
            if (vehicle.getStatus().equals(VehicleStatusEnum.ESPERA_REVISION_LEGAL.name())){ //It should only save the paperwork if the vehicle has a different status
                vehicle.setStatus(VehicleStatusEnum.ESPERA_REVISION_TECNICA.name());
            }            
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

    public String updateDni(UpdateDniTO request)throws EngineException{
        if (request.getPlate() == null || request.getNewDni() == null)
            throw new EngineException("Ingrese los datos necesarios", HttpStatus.BAD_REQUEST);
        
        VehicleDE vehicle = service.getByPlate(request.getPlate());

        if (vehicle == null)
            throw new EngineException("No se encontró el vehiculo", HttpStatus.BAD_REQUEST);

        vehicle.setDni(request.getNewDni());
        service.save(vehicle);    
        return "El dni ha sido actualizado";
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

    public String enableVehicle(EnableVehicleTO request) throws EngineException{
        if (!validateBranchPhoto(request))
            throw new EngineException("ingrese los datos necesarios", HttpStatus.BAD_REQUEST);
        
        VehicleDE vehicle = service.getByPlate(request.getPlate());

        if (vehicle == null)
            throw new EngineException("No se encontró el vehiculo", HttpStatus.BAD_REQUEST);

        if (!vehicle.getStatus().equals(VehicleStatusEnum.COMPRADO.name()))
            throw new EngineException("El vehiculo no ha sido comprado", HttpStatus.BAD_REQUEST);

        vehicle.setPicture1(request.getPhoto1());
        vehicle.setPicture2(request.getPhoto2());
        vehicle.setPicture3(request.getPhoto3());
        vehicle.setBranch(request.getBranch());
        vehicle.setStatus(VehicleStatusEnum.DISPONIBLE.name());
        service.save(vehicle);
        
        return "El vehiculo ha sido habilitado para su venta";
    }

    public String rejectVehicle(String plate) throws EngineException{
        VehicleDE vehicle = service.getByPlate(plate);
        validateVehicleFinalStatus(vehicle);
        vehicle.setStatus(VehicleStatusEnum.RECHAZADO.name());
        service.save(vehicle);
        return "El vehiculo "+plate+" fue RECHAZADO";
    }

    public String acceptVehicle(String plate) throws EngineException{
        VehicleDE vehicle = service.getByPlate(plate);
        validateVehicleFinalStatus(vehicle);
        vehicle.setStatus(VehicleStatusEnum.ACEPTADO.name());
        service.save(vehicle);
        paymentFacade.sendDebitPayment(vehicle);

        if(vehicle.getScore()<100){
            vehicle.setStatus(VehicleStatusEnum.EN_REPARACION.name());
            service.save(vehicle);
        } else {
            vehicle.setStatus(VehicleStatusEnum.COMPRADO.name());
            service.save(vehicle);
        }  
        return "El vehiculo "+plate+" fue ACEPTADO";
    }

    public String exchangeVehicle(String plate) throws EngineException{
        VehicleDE vehicle = service.getByPlate(plate);
        validateVehicleFinalStatus(vehicle);
        if(vehicle.getScore()<100){
            vehicle.setStatus(VehicleStatusEnum.EN_REPARACION.name());
            service.save(vehicle);
            return "El vehiculo "+plate+" fue actualizado: EN REPARACION";
        } else {
            vehicle.setStatus(VehicleStatusEnum.COMPRADO.name());
            service.save(vehicle);
            return "El vehiculo "+plate+" fue actualizado: COMPRADO";
        }
    }

    private void validateVehicleFinalStatus(VehicleDE vehicle) throws EngineException {
        if (vehicle == null) {
            throw new EngineException("El vehiculo no existe", HttpStatus.BAD_REQUEST);
        } else if (!vehicle.getStatus().equals(VehicleStatusEnum.ESPERA_DECISION_FINAL.name())){
            throw new EngineException("El vehiculo no esta en espera de decision final", HttpStatus.BAD_REQUEST);
        }
    }

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

    private boolean validatePlate(String plate){

        Pattern pattern1964 = Pattern.compile("^[A-Za-z]\\d{7}$");
        Pattern pattern1994 = Pattern.compile("^[A-Za-z]{3}\\d{3}$");
        Pattern pattern2016 = Pattern.compile("^[A-Za-z]{2}\\d{3}[A-Za-z]{2}$");

        Matcher matcher1964 = pattern1964.matcher(plate);
        Matcher matcher1994 = pattern1994.matcher(plate);
        Matcher matcher2016 = pattern2016.matcher(plate);

        return matcher1964.matches() || matcher1994.matches() || matcher2016.matches();
    }

    private boolean validateModelField(ModelTO request){
        return request.getBasePrice() != null 
                && request.getBrand() != null 
                && request.getModel() !=null 
                && request.getYear() !=null 
                && request.getEngine() !=null 
                && request.getFuelType() != null;
    }

    private boolean validateBranchPhoto(EnableVehicleTO request){
        return request.getPlate() !=null 
               && request.getBranch() != null 
               && request.getPhoto1() != null 
               && request.getPhoto2() != null 
               && request.getPhoto3() != null;
    }

}
    