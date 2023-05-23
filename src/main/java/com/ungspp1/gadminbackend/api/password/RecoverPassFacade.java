package com.ungspp1.gadminbackend.api.password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.ungspp1.gadminbackend.api.password.to.NewPassRequest;
import com.ungspp1.gadminbackend.api.password.to.RecoverPassRequestTO;
import com.ungspp1.gadminbackend.api.password.to.TokenRequestTO;
import com.ungspp1.gadminbackend.exceptions.EngineException;

@Component
public class RecoverPassFacade {
    
    @Autowired
    private RecoverPassService recoverPassService;
    
    public HttpStatus verifyEmail(RecoverPassRequestTO request) throws EngineException{
        return recoverPassService.verifyEmail(request);
    }
    
    public HttpStatus verifyToken(TokenRequestTO request) throws EngineException{
        return recoverPassService.verifyToken(request);
    }

    public HttpStatus uploadNewPass(NewPassRequest request) throws EngineException{
        return recoverPassService.uploadNewPass(request);
    }
}
