package com.ungspp1.gadminbackend.api.vehicle.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ungspp1.gadminbackend.api.vehicle.to.ModelTO;
import com.ungspp1.gadminbackend.api.vehicle.to.PaperworkTO;
import com.ungspp1.gadminbackend.api.vehicle.to.VehicleRequestTO;
import com.ungspp1.gadminbackend.model.entity.ModelDE;
import com.ungspp1.gadminbackend.model.entity.PaperworkDE;
import com.ungspp1.gadminbackend.model.entity.VehicleDE;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    VehicleDE requestToDE(VehicleRequestTO request);
    ModelDE   requestModelToDE (ModelTO modelTO);
    PaperworkDE requestPaperworkToDE (PaperworkTO paperworkTO);
    VehicleRequestTO vehicleRequestToDE(VehicleDE vehicleDE);
    List<VehicleRequestTO> vehicleDEtoRequestTOList(List<VehicleDE> vehicleDEs);

    @Mapping (target = "modelData" , source = "de")
    VehicleDE requestToDEWithModel(VehicleRequestTO request, ModelDE de);

}