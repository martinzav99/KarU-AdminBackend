package com.ungspp1.gadminbackend.api.password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.ungspp1.gadminbackend.api.password.to.NewPassRequest;
import com.ungspp1.gadminbackend.api.password.to.RecoverPassRequestTO;
import com.ungspp1.gadminbackend.exceptions.EngineException;

@Component
public class RecoverPassFacade {
    
    @Autowired
    private RecoverPassService recoverPassService;
    
    public HttpStatus resetPassword(RecoverPassRequestTO request) throws EngineException{
        return recoverPassService.resetPassword(request);
    }
    
    public Boolean verifyToken(String token) throws EngineException{
        return recoverPassService.verifyToken(token);
    }

    public HttpStatus uploadNewPass(String token, NewPassRequest request) throws EngineException{
        return recoverPassService.uploadNewPass(token,request);
    }
}
