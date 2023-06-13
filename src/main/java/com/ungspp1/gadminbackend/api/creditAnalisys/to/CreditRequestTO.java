package com.ungspp1.gadminbackend.api.creditAnalisys.to;

import java.math.BigInteger;

//import java.math.BigDecimal;
//import java.math.BigInteger;

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
public class CreditRequestTO{
    private double totalIngres;
    private double deudas;
    private double monthPayments;
    private BigInteger memberFamilyAs;
    private BigInteger memberFamilyNoAs;
    private double memberFamilyIngress;
    private String jobState;
    private String cuit;
}