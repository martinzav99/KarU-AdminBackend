package com.ungspp1.gadminbackend.api.priceHistory.mapper;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ungspp1.gadminbackend.api.priceHistory.to.PriceHistoryTO;
import com.ungspp1.gadminbackend.model.entity.PriceHistoryDE;

@Mapper(componentModel = "spring", imports = {ZonedDateTime.class, ZoneId.class})
public interface PriceHistoryMapper {

    @Mapping(target = "updateDate", expression = "java(ZonedDateTime.now(ZoneId.of(\"America/Argentina/Buenos_Aires\")))")
    PriceHistoryDE historyRequestToHistoryDE (PriceHistoryTO requestTO);

    PriceHistoryTO deToResponseTO(PriceHistoryDE de);

    List<PriceHistoryTO> deListToResponseTO (List<PriceHistoryDE> deList);

  
}
