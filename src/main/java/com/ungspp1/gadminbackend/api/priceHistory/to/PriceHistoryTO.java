package com.ungspp1.gadminbackend.api.priceHistory.to;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceHistoryTO {
    private String reference;
    private String message;
    private Float newSellPrice;
    private Float newBasePrice;
    private ZonedDateTime updateDate;
    private Float massivePercentage;

}
