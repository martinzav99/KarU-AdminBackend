package com.ungspp1.gadminbackend.api.variables;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.model.entity.VariableDE;
import com.ungspp1.gadminbackend.model.repository.VariablesRepository;

@Service
public class VariablesService {
    @Autowired
    private VariablesRepository repository;

    public float getVariable(String name) throws EngineException{
        VariableDE variable = repository.findByName(name).orElse(null);
        if(variable == null){
            throw new EngineException("La variable "+name+" no existe", HttpStatus.BAD_REQUEST);
        } else {
            return variable.getValue();
        }
    }

    public void updateVariable(String name, Float value) throws EngineException{
        VariableDE variable = repository.findByName(name).orElse(null);
        if(variable == null){
            throw new EngineException("La variable "+name+" no existe", HttpStatus.BAD_REQUEST);
        } else {
            variable.setValue(value);
            repository.save(variable);
        }
    }

    public List<VariableDE> getAll(){
        return repository.findAll();
    }
}
