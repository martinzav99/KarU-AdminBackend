package com.ungspp1.gadminbackend.api.creditAnalisys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.model.entity.CreditAnalisysDE;
import com.ungspp1.gadminbackend.model.repository.CreditAnalisysRepository;

@Service
public class CreditAnalisysService {
    
    @Autowired
    private CreditAnalisysRepository repository;

    public void save(CreditAnalisysDE de){
        repository.save(de);
    }

    public CreditAnalisysDE findByDocument (String document) throws EngineException{
        return repository.findByDocument(document).orElse(null);
    }

} 