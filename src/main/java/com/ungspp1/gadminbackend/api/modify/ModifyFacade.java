package com.ungspp1.gadminbackend.api.modify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ungspp1.gadminbackend.api.modify.to.ModifyRequestTO;
import com.ungspp1.gadminbackend.api.modify.to.ModifyResponseTO;
import com.ungspp1.gadminbackend.exceptions.EngineException;

@Component
public class ModifyFacade {
    
    @Autowired
    private ModifyService modifyService;
    
    public ModifyResponseTO updateUserData(ModifyRequestTO request) throws EngineException{
        return modifyService.updateUserData(request);
    }
}
