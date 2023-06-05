package com.ungspp1.gadminbackend.api.priceHistory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.ungspp1.gadminbackend.api.priceHistory.mapper.PriceHistoryMapper;
import com.ungspp1.gadminbackend.api.priceHistory.to.PriceHistoryTO;
import com.ungspp1.gadminbackend.api.vehicle.VehicleService;
import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.model.entity.PriceHistoryDE;
import com.ungspp1.gadminbackend.model.entity.VehicleDE;


@Component
public class PriceHistoryFacade {

    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private PriceHistoryMapper mapper;
    @Autowired
    private PriceHistoryService service;

    public PriceHistoryTO newVehiclePriceHistory(String reference, Float newSellPrice, String message) throws EngineException {
        PriceHistoryTO request = PriceHistoryTO.builder().reference(reference).message(message).newSellPrice(newSellPrice).build();
        PriceHistoryDE newPrice = mapper.historyRequestToHistoryDE(request);
        return mapper.deToResponseTO(service.save(newPrice));
    }

    public PriceHistoryTO newModelPriceHistory(String reference, Float newBasePrice, String message) throws EngineException {
        PriceHistoryTO request = PriceHistoryTO.builder().reference(reference).message(message).newBasePrice(newBasePrice).build();
        PriceHistoryDE newPrice = mapper.historyRequestToHistoryDE(request);
        return mapper.deToResponseTO(service.save(newPrice));
    }

    public PriceHistoryTO vehiclePriceChangeHistory(String reference, String message, Float newSellPrice) throws EngineException {
        VehicleDE vehicle = vehicleService.getByPlate(reference);
        if (vehicle != null){
            PriceHistoryTO request = PriceHistoryTO.builder().reference(reference).message(message).newSellPrice(newSellPrice).build();
            PriceHistoryDE newPrice = mapper.historyRequestToHistoryDE(request);
            return mapper.deToResponseTO(service.save(newPrice));
        } else {
            throw new EngineException("Intenta cambiar el precio a un vehiculo inexistente: " + reference, HttpStatus.BAD_REQUEST);
        }
    }

    public PriceHistoryTO modelPriceChangeHistory(String reference, String message, Float newBasePrice) throws EngineException {
        PriceHistoryTO request = PriceHistoryTO.builder().reference(reference).message(message).newBasePrice(newBasePrice).build();
        PriceHistoryDE newPrice = mapper.historyRequestToHistoryDE(request);
        return mapper.deToResponseTO(service.save(newPrice));
    }

    public PriceHistoryTO saveMassiveChangeHistory(String reference, String message, Float percentage) throws EngineException {
        PriceHistoryTO request = PriceHistoryTO.builder().reference(reference).message(message).massivePercentage(percentage).build();
        PriceHistoryDE newPrice = mapper.historyRequestToHistoryDE(request);
        return mapper.deToResponseTO(service.save(newPrice));
    }

    public List<PriceHistoryTO> getAll() {
      List <PriceHistoryTO> historyTO = mapper.deListToResponseTO(service.getAll());
       return historyTO;
    }
}
    

