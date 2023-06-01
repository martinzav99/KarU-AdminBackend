package com.ungspp1.gadminbackend.api.variables;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ungspp1.gadminbackend.api.variables.mapper.VariablesMapper;
import com.ungspp1.gadminbackend.api.variables.to.VariableTO;
import com.ungspp1.gadminbackend.exceptions.EngineException;

@Component
public class VariablesFacade {
    @Autowired
    private VariablesService variablesService;
    @Autowired
    private VariablesMapper variablesMapper;

    public float getVariableValue(String name) throws EngineException{
        return variablesService.getVariable(name);
    }

    public List<VariableTO> getAllVariables() throws EngineException{
        return variablesMapper.deListToToList(variablesService.getAll());
    }

    public String updateVariableValue(VariableTO request) throws EngineException{
        variablesService.updateVariable(request.getName(), request.getValue());
        return "Variable actualizada";
    }

}
    