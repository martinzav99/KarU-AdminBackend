package com.ungspp1.gadminbackend.api.vehicle.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ungspp1.gadminbackend.api.vehicle.to.ModelTO;
import com.ungspp1.gadminbackend.api.vehicle.to.PaperworkTO;
import com.ungspp1.gadminbackend.api.vehicle.to.VehicleResponseTO;
import com.ungspp1.gadminbackend.api.vehicle.to.VehicleTO;
import com.ungspp1.gadminbackend.model.entity.ModelDE;
import com.ungspp1.gadminbackend.model.entity.PaperworkDE;
import com.ungspp1.gadminbackend.model.entity.VehicleDE;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    @Mapping(source="paperworkData.debt", target = "debt")
    @Mapping(source="paperworkData.vpa", target ="vpa")
    @Mapping(source="paperworkData.rva", target ="rva")
    @Mapping(source="paperworkData.vtv", target ="vtv")
    @Mapping(source="modelData.basePrice", target="basePrice")
    @Mapping(source="modelData.brand", target="brand")
    @Mapping(source="modelData.model", target="model")
    @Mapping(source="modelData.year", target="year")
    @Mapping(source="modelData.fuelType", target="fuelType")
    @Mapping(source="modelData.engine", target="engine")
    @Mapping(source="modelData.category", target="category")
    VehicleResponseTO deToResponseTO (VehicleDE vehicleDE);

    List<VehicleResponseTO> deListToResponseList(List<VehicleDE> vehicleDEs);

    ModelDE requestModelToDE (ModelTO modelTO);

    ModelTO modelDEtoTO (ModelDE modelDE);

    List<ModelTO> modelDEsToTOs(List<ModelDE> modelDEs);

    PaperworkDE requestPaperworkToDE (PaperworkTO paperworkTO);

    VehicleTO vehicleRequestToDE(VehicleDE vehicleDE);
    
    List<VehicleTO> vehicleDEtoRequestTOList(List<VehicleDE> vehicleDEs);

    @Mapping (target = "paperworkData" , source = "paperworkDE")
    VehicleDE requestToDE(VehicleTO request, PaperworkDE paperworkDE);
   
    @Mapping (target = "paperworkData" , source = "paperworkDE")
    @Mapping (target = "modelData" , source = "de")
    VehicleDE requestToDEWithModel(VehicleTO request, ModelDE de, PaperworkDE paperworkDE);

}