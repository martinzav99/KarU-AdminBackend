package com.ungspp1.gadminbackend.creditAnalisys.to;

import java.math.BigDecimal;
import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class CurrentAcountRequestTO{
    private double incomeBalance;
    private double exitBalance;
    private boolean aproved;
}