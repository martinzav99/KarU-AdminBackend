package com.ungspp1.gadminbackend.api.signup;

import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.model.enums.SessionStatusEnum;

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
    private final List<String> branchUserType = Arrays.asList(new String[]{"GERENTE_SUCURSAL", "SUPERVISOR_VENTAS", "SUPERVISOR_TECNICO", "ADMINISTRADOR", "TECNICO", "VENDEDOR"});
    private final List<String> noBranchUserType = Arrays.asList(new String[]{"GERENTE_GENERAL", "IT"});
    private final List<String> technicalLevel = Arrays.asList(new String[]{"A","B","C"});

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
        request.setType("CLIENTE");
    }

    public void validateInternalUser(SignupUserRequestTO request) throws EngineException{
        String type = request.getType().toUpperCase();
        if (!branchUserType.contains(type) && !noBranchUserType.contains(type)){
            throw new EngineException("The user type must be in:" + branchUserType + noBranchUserType, HttpStatus.BAD_REQUEST);
        } 
    
        if (request.getType().equals("TECNICO") && !technicalLevel.contains(request.getTechnicalLevel())){
            throw new EngineException("All technical operators must have a technical level: " + technicalLevel, HttpStatus.BAD_REQUEST);
        } else if (!request.getType().equals("TECNICO")){
            request.setTechnicalLevel(null);
        }

        if (branchUserType.contains(type) && request.getBranch() == null){
            throw new EngineException("The branch cant be null for "+ type + " users", HttpStatus.BAD_REQUEST);
        } else if (noBranchUserType.contains(type)){
            request.setBranch(null);
        }
        request.setType(type);
    }
}
