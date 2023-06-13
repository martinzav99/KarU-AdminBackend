package com.ungspp1.gadminbackend.api.creditAnalisys.facade;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ungspp1.gadminbackend.api.creditAnalisys.creditAnalisysService;
import com.ungspp1.gadminbackend.api.creditAnalisys.to.CreditRequestTO;
import com.ungspp1.gadminbackend.api.creditAnalisys.to.CreditResponseTO;
import com.ungspp1.gadminbackend.api.variables.VariablesService;
import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.model.entity.CreditAnalisysDE;

@Component
public class creditAnalisysFacade {
    @Autowired 
    VariablesService service;
    @Autowired
    creditAnalisysService analisysService;  

    public CreditResponseTO analizeCredit(CreditRequestTO request) throws EngineException {
                CreditResponseTO responseTO;
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
                            responseTO = CreditResponseTO.builder().resultCredit("BUENO").build();
                        } else {
                            responseTO = CreditResponseTO.builder().resultCredit("REGULAR").build();
                        }
                    } else {
                        responseTO =  CreditResponseTO.builder().resultCredit("REGULAR").build();
                    }
                } else if (ingresosTotales < ingresosDefault && deudas > deudasDefault) {
                     responseTO =  CreditResponseTO.builder().resultCredit("MALO").build();
                } else {
                    if (estadoLaboral.equals("desempleado")) {
                        responseTO = CreditResponseTO.builder().resultCredit("MALO").build();
                    } else {
                        responseTO = CreditResponseTO.builder().resultCredit("REGULAR").build();
                    }
                }
                
                CreditAnalisysDE analisysDE = analisysService.findByDocument(request.getDni());
                if (analisysDE != null){
                    analisysDE.setValue(responseTO.getResultCredit());
                    analisysService.save(analisysDE);
                }else {
                    analisysService.save(CreditAnalisysDE.builder().document(request.getDni()).value(responseTO.getResultCredit()).build());
                }

                return responseTO;
        }

    public CreditResponseTO findByDocument(String document) throws EngineException {
        CreditAnalisysDE analisysDE = analisysService.findByDocument(document);
        return CreditResponseTO.builder().document(analisysDE.getDocument()).resultCredit(analisysDE.getValue()).build();
    }
    }


