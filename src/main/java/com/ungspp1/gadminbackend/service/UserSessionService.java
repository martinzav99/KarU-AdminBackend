package com.ungspp1.gadminbackend.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ungspp1.gadminbackend.api.utils.DateUtils;
import com.ungspp1.gadminbackend.model.entity.UserDE;
import com.ungspp1.gadminbackend.model.entity.UserSessionDE;
import com.ungspp1.gadminbackend.model.enums.TwoFactorStatusEnum;
import com.ungspp1.gadminbackend.model.repository.UserSessionRepository;
import com.ungspp1.gadminbackend.model.to.UserSessionTO;
import com.ungspp1.gadminbackend.model.to.ValidatedUserSessionTO;

@Service
public class UserSessionService {
    @Autowired
    private UserSessionRepository sessionRepository;

    public ValidatedUserSessionTO validateSession(String user, String password, String code){
        UserSessionDE sessionDE = getSessionByUser(user, password);
        UserSessionTO sessionTO = UserSessionTO.builder().sessionGenerationDate(LocalDateTime.now()).codeStatus(TwoFactorStatusEnum.USED.name()).build();
        if (sessionDE.getId() == null) {
            return ValidatedUserSessionTO.builder().username(user).validation(false).build();
        } 
        if (sessionDE.getTwoFactorCode().equals(code) && sessionDE.getCodeStatus().equals(TwoFactorStatusEnum.NEW.name()) 
        && DateUtils.minutesBetweenDates(sessionDE.getCodeGenerationDate(), LocalDateTime.now()) <= 5){
            createOrUpdateSession(sessionDE.getUserData(), sessionTO);
            return ValidatedUserSessionTO.builder().username(user).type(sessionDE.getUserData().getType()).validation(true).build();
        }
        return ValidatedUserSessionTO.builder().username(user).validation(false).build();
    }

    public UserSessionDE getSessionByUser(String user, String password){
        return sessionRepository.findByUserAndPassword(user, password).orElse(new UserSessionDE());
    }

    public void createOrUpdateSession(UserDE user, UserSessionTO sessionTO){
        UserSessionDE sessionDE = getSessionByUser(user.getUsername(), user.getPassword());
        if(sessionDE.getUserData() == null){
            sessionDE.setUserData(user);
        }
        if(sessionTO.getTwoFactorCode() != null){
            sessionDE.setTwoFactorCode(sessionTO.getTwoFactorCode());
        }
        if(sessionTO.getCodeStatus() != null) {
            sessionDE.setCodeStatus(sessionTO.getCodeStatus());
        }
        if(sessionTO.getCodeGenerationDate() != null) {
            sessionDE.setCodeGenerationDate(sessionTO.getCodeGenerationDate());
        }
        if(sessionTO.getSessionGenerationDate() != null) {
            sessionDE.setSessionGenerationDate(sessionTO.getSessionGenerationDate());
        }
        sessionRepository.save(sessionDE);
    }
}
