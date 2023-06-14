package com.ungspp1.gadminbackend.api.login.mapper;

import org.mapstruct.Mapper;

import com.ungspp1.gadminbackend.api.login.to.LoginResponseTO;
import com.ungspp1.gadminbackend.model.entity.UserDE;
import com.ungspp1.gadminbackend.model.enums.SessionStatusEnum;

@Mapper(componentModel = "spring")
public interface LoginMapper {
    
    default LoginResponseTO userDEToResponseTO(UserDE user){
        return LoginResponseTO.builder()
            .username(user.getUsername())
            .type(user.getType())
            .sessionStatus(SessionStatusEnum.USUARIO_ENCONTRADO.name())
            .branch(user.getBranch())
            .id(user.getId())
            .build();
    }

    default LoginResponseTO userNotFound(){
        return LoginResponseTO.builder().sessionStatus(SessionStatusEnum.USUARIO_NO_ENCONTRADO.name()).build();
    }

    default LoginResponseTO sessionNotConfirmed(){
        return LoginResponseTO.builder().sessionStatus(SessionStatusEnum.SESION_NO_CONFIRMADA.name()).build();
    }
}
