package com.ungspp1.gadminbackend.api.price.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ungspp1.gadminbackend.api.price.to.PriceHistoryRequestTO;
import com.ungspp1.gadminbackend.api.price.to.PriceResponseTO;
import com.ungspp1.gadminbackend.model.entity.PriceHistoryDE;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring" , imports = {LocalDateTime.class})
public interface PriceMapper {
   
    @Mapping(target = "fecha", expression = "java(LocalDateTime.now())")
    PriceHistoryDE historyRequestToHistoryDE (PriceHistoryRequestTO requestTO);

    PriceResponseTO deToResponseTO(PriceHistoryDE de);
}
