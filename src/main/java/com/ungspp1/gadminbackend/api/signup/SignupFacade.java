package com.ungspp1.gadminbackend.api.signup;

import com.ungspp1.gadminbackend.exceptions.EngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.ungspp1.gadminbackend.api.login.LoginFacade;
import com.ungspp1.gadminbackend.api.login.to.LoginResponseTO;
import com.ungspp1.gadminbackend.api.signup.to.SignupUserRequestTO;
import com.ungspp1.gadminbackend.api.signup.to.SignupUserResponseTO;
import com.ungspp1.gadminbackend.enums.SessionStatusEnum;

@Component
public class SignupFacade {

    @Autowired
    private SignupService signupService;
    @Autowired
    private LoginFacade loginFacade;
    
    //TODO: Validar que los datos de contacto no esten repetidos
    public SignupUserResponseTO saveUser(SignupUserRequestTO request) throws EngineException {
        validateRepeatedUsername(request.getUsername());
        signupService.validateRepeatedContact(request);
        return signupService.saveUser(request);
    }

    private void validateRepeatedUsername(String username) throws EngineException{
        LoginResponseTO response = loginFacade.getUserByUsername(username);
        if(response.getSessionStatus().equals(SessionStatusEnum.USUARIO_ENCONTRADO.name())){
            throw new EngineException("The username is already in use", HttpStatus.BAD_REQUEST);
        }
    }
}
