package com.ungspp1.gadminbackend.api.login.mapper;

import org.mapstruct.Mapper;

import com.ungspp1.gadminbackend.api.login.to.LoginResponseTO;
import com.ungspp1.gadminbackend.entity.UserDE;
import com.ungspp1.gadminbackend.enums.SessionStatusEnum;

@Mapper(componentModel = "spring")
public interface LoginMapper {
    
    default LoginResponseTO userDEToResponseTO(UserDE user){
        return LoginResponseTO.builder().type(user.getType()).sessionStatus(SessionStatusEnum.USUARIO_ENCONTRADO.name()).build();
    }

    default LoginResponseTO userNotFound(){
        return LoginResponseTO.builder().sessionStatus(SessionStatusEnum.USUARIO_NO_ENCONTRADO.name()).build();
    }
}
