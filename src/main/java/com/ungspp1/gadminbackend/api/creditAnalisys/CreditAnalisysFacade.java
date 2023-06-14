package com.ungspp1.gadminbackend.api.creditAnalisys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.ungspp1.gadminbackend.api.creditAnalisys.to.CreditRequestTO;
import com.ungspp1.gadminbackend.api.creditAnalisys.to.CreditResponseTO;
import com.ungspp1.gadminbackend.api.variables.VariablesService;
import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.model.entity.CreditAnalisysDE;
import com.ungspp1.gadminbackend.model.enums.CreditScoreEnum;

@Component
public class CreditAnalisysFacade {
    @Autowired 
    private VariablesService variableService;
    @Autowired
    private CreditAnalisysService analisysService;  


    public CreditResponseTO generateScore(CreditRequestTO request) throws EngineException {
        String score;
        if(request.getUnemployed()){
            score = CreditScoreEnum.MALO.name();
        } else {
            double totalIncome = request.getFamilyMembersIncome() + request.getTotalIncome();
            double monthlyFinalSavings = totalIncome/(request.getUnemployedFamilyMembers()+1) - request.getMonthlyOutcome() - request.getDebts(); 
            float minimumSavings = variableService.getVariable("AHORRO_MINIMO_CREDITO");
            float recommendedSavings = variableService.getVariable("AHORRO_RECOMENDADO_CREDITO");
            
            if(monthlyFinalSavings < minimumSavings){
                score = CreditScoreEnum.MALO.name();
            } else if (monthlyFinalSavings < recommendedSavings){
                score = CreditScoreEnum.REGULAR.name();
            } else {
                score = CreditScoreEnum.BUENO.name();
            }
        }
        CreditResponseTO responseTO = CreditResponseTO.builder().document(request.getDocument()).score(score).build();
        CreditAnalisysDE analisysDE = analisysService.findByDocument(request.getDocument());
        
        if (analisysDE != null){
            analisysDE.setScore(responseTO.getScore());
            analisysService.save(analisysDE);
        }else {
            analisysService.save(CreditAnalisysDE.builder().document(responseTO.getDocument()).score(responseTO.getScore()).build());
        }

        return responseTO;
    }


    public CreditResponseTO findByDocument(String document) throws EngineException {
        CreditAnalisysDE analisysDE = analisysService.findByDocument(document);
        if (analisysDE == null){
            throw new EngineException("No se encontró analisis crediticio para el DNI "+document, HttpStatus.BAD_REQUEST);
        } else {
            return CreditResponseTO.builder().document(analisysDE.getDocument()).score(analisysDE.getScore()).build();
        }
    }

}


