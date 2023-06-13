package com.ungspp1.gadminbackend.api.creditAnalisys.facade;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ungspp1.gadminbackend.api.creditAnalisys.to.CreditRequestTO;
import com.ungspp1.gadminbackend.api.creditAnalisys.to.CreditResponseTO;
import com.ungspp1.gadminbackend.api.variables.VariablesService;
import com.ungspp1.gadminbackend.exceptions.EngineException;

@Component
public class creditAnalisysFacade {
    @Autowired 
    VariablesService service;
    public CreditResponseTO analizeCredit(CreditRequestTO request) throws EngineException {
                double ingresosTotales = (request.getMemberFamilyIngress()+ request.getTotalIngres()) / request.getMemberFamilyNoAs().intValue();
                double gastosMensuales = request.getMonthPayments();
                double deudas = request.getDeudas();
                String estadoLaboral = request.getJobState();
                float gastosDefault = service.getVariable("MINIMO_GASTOS");
                float ingresosDefault = service.getVariable("MINIMO_INGRESOS");
                float deudasDefault =  service.getVariable("MINIMO_DEUDAS");
                if (ingresosTotales > ingresosDefault && deudas < deudasDefault) {
                    if (request.getMemberFamilyAs().compareTo(BigInteger.ZERO) > 0 && request.getMemberFamilyAs().compareTo(BigInteger.valueOf(4))<0) {
                        if (gastosMensuales < gastosDefault) {
                            return CreditResponseTO.builder().resultCredit("BUENO").build();
                        } else {
                            return CreditResponseTO.builder().resultCredit("REGULAR").build();
                        }
                    } else {
                        return  CreditResponseTO.builder().resultCredit("REGULAR").build();
                    }
                } else if (ingresosTotales < ingresosDefault && deudas > deudasDefault) {
                     return CreditResponseTO.builder().resultCredit("MALO").build();
                } else {
                    if (estadoLaboral.equals("desempleado")) {
                        return CreditResponseTO.builder().resultCredit("MALO").build();
                    } else {
                        return CreditResponseTO.builder().resultCredit("REGULAR").build();
                    }
                }
        }
    }


