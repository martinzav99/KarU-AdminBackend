package com.ungspp1.gadminbackend.api.login;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ungspp1.gadminbackend.api.login.mapper.LoginMapper;
import com.ungspp1.gadminbackend.api.login.to.LoginRequestTO;
import com.ungspp1.gadminbackend.api.login.to.LoginResponseTO;
import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.model.entity.UserDE;
import com.ungspp1.gadminbackend.model.enums.SessionStatusEnum;
import com.ungspp1.gadminbackend.model.enums.TwoFactorStatusEnum;
import com.ungspp1.gadminbackend.model.repository.UserRepository;
import com.ungspp1.gadminbackend.model.to.UserSessionTO;
import com.ungspp1.gadminbackend.model.to.ValidatedUserSessionTO;
import com.ungspp1.gadminbackend.service.SendMailService;
import com.ungspp1.gadminbackend.service.UserSessionService;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SendMailService mailFacade;

    @Autowired
    private UserSessionService sessionService;

    @Autowired
    private LoginMapper loginMapper;


    public LoginResponseTO getUserByUsername(String username){
        Optional<UserDE> userDE = userRepository.findByUsername(username);
        if (userDE.isPresent()){
            return loginMapper.userDEToResponseTO(userDE.get());
        } else {
            return loginMapper.userNotFound();
        }
    }

    public LoginResponseTO loginUser(LoginRequestTO loginRequestTO) {
        try{
            UserDE user = getUserByUsernameAndPassword(loginRequestTO);
            startTwoFactorAuth(user);
            return loginMapper.userDEToResponseTO(user);
        } catch (EngineException e){
            return loginMapper.userNotFound();
        }
    }

    public LoginResponseTO validateAuthCode(LoginRequestTO request){
        ValidatedUserSessionTO validatedSession = sessionService.validateSession(request.getUsername(), request.getPassword(), request.getTwoFactorCode());
        if(validatedSession.getValidation()){
            return LoginResponseTO.builder().sessionStatus(SessionStatusEnum.SESION_CONFIRMADA.name()).type(validatedSession.getType()).build();
        } else {
            return loginMapper.sessionNotConfirmed();
        }
    }

    private void startTwoFactorAuth(UserDE user){
        String code = RandomStringUtils.randomAlphanumeric(5);
        UserSessionTO sessionTO = UserSessionTO.builder()
            .codeGenerationDate(LocalDateTime.now())
            .codeStatus(TwoFactorStatusEnum.NEW.name())
            .twoFactorCode(code)
            .build();
        sessionService.createOrUpdateSession(user, sessionTO);
        mailFacade.sendAutentcathionMail(user.getContactData().getEmail(), code);
    }

    private UserDE getUserByUsernameAndPassword(LoginRequestTO request) throws EngineException{
        Optional<UserDE> userDE = userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        if(userDE.isPresent()){
            return userDE.get();
        } else {
            throw new EngineException("Usuario no encontrado", HttpStatus.BAD_REQUEST);
        }
    }
    
}
