package com.ungspp1.gadminbackend.api.modify.mapper;

import org.mapstruct.Mapper;

import com.ungspp1.gadminbackend.api.modify.to.ModifyResponseTO;
import com.ungspp1.gadminbackend.entity.UserDE;

@Mapper(componentModel = "spring")
public interface ModifyMapper {
    
    public ModifyResponseTO userDEToResponse(UserDE user);
}
