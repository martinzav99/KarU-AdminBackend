package com.ungspp1.gadminbackend.creditAnalisys.to;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CurrentAcountRequestTO{
    private BigInteger incomeBalance;
    private BigInteger exitBalance;
}