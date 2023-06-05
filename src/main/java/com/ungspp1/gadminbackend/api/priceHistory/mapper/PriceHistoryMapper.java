package com.ungspp1.gadminbackend.api.priceHistory.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ungspp1.gadminbackend.api.priceHistory.to.PriceHistoryTO;
import com.ungspp1.gadminbackend.model.entity.PriceHistoryDE;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface PriceHistoryMapper {

    @Mapping(target = "updateDate", expression = "java(LocalDateTime.now())")
    PriceHistoryDE historyRequestToHistoryDE (PriceHistoryTO requestTO);

    PriceHistoryTO deToResponseTO(PriceHistoryDE de);

    List<PriceHistoryTO> deListToResponseTO (List<PriceHistoryDE> deList);

  
}
