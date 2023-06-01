package com.ungspp1.gadminbackend.api.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.ungspp1.gadminbackend.api.price.mapper.PriceMapper;
import com.ungspp1.gadminbackend.api.price.to.PriceHistoryRequestTO;
import com.ungspp1.gadminbackend.api.price.to.PriceResponseTO;
import com.ungspp1.gadminbackend.api.vehicle.VehicleService;
import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.model.entity.PriceHistoryDE;
import com.ungspp1.gadminbackend.model.entity.VehicleDE;


@Component
public class PriceFacade {
    @Autowired
    VehicleService vehicleService;
    @Autowired
    PriceMapper mapper;
    @Autowired
    PriceService service;



    public PriceResponseTO savePrice(PriceHistoryRequestTO request) throws EngineException {
        VehicleDE vehicle = vehicleService.getByPlate(request.getReferencia());
        if (vehicle != null){
                PriceHistoryDE newPrice = mapper.historyRequestToHistoryDE(request);
                return mapper.deToResponseTO(service.save(newPrice));
        } else {
            throw new EngineException("Intenta cambiar el precio a un vehiculo inexistente" + request.getReferencia(), HttpStatus.BAD_REQUEST);
        }
    }
}
    

