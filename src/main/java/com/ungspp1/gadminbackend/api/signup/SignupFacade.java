package com.ungspp1.gadminbackend.api.signup;

import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.model.enums.SessionStatusEnum;
import com.ungspp1.gadminbackend.model.enums.UserTypeEnum;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.ungspp1.gadminbackend.api.login.LoginFacade;
import com.ungspp1.gadminbackend.api.login.to.LoginResponseTO;
import com.ungspp1.gadminbackend.api.signup.to.SignupUserRequestTO;
import com.ungspp1.gadminbackend.api.signup.to.SignupUserResponseTO;

@Component
public class SignupFacade {

    @Autowired
    private SignupService signupService;
    @Autowired
    private LoginFacade loginFacade;
    private final List<String> internalUserType = Arrays.asList(new String[]{"TECNICO", "ADMINISTRADOR", "SUPERVISOR", "VENDEDOR"});
    
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

    public void normalizeClientUser(SignupUserRequestTO request) {
        request.setBranch(null);
        request.setTechnicalLevel(null);
        request.setType(UserTypeEnum.CLIENTE.name());
    }

    public void validateInternalUser(SignupUserRequestTO request) throws EngineException{
        String type = request.getType().toUpperCase();
        if (!internalUserType.contains(type)){
            throw new EngineException("The user type is not valid", HttpStatus.BAD_REQUEST);
        } else if (request.getBranch() == null){
            throw new EngineException("The branch cant be null for internal users", HttpStatus.BAD_REQUEST);
        } else if (request.getType().equals("TECNICO") && request.getTechnicalLevel() == null){
            throw new EngineException("All technical operators must have a technical level", HttpStatus.BAD_REQUEST);
        }
        request.setType(type);
    }
}
