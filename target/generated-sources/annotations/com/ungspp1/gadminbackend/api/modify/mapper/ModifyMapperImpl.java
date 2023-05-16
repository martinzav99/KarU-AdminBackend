package com.ungspp1.gadminbackend.api.modify.mapper;

import com.ungspp1.gadminbackend.api.modify.to.ModifyResponseTO;
import com.ungspp1.gadminbackend.model.entity.UserDE;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-16T17:58:24-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class ModifyMapperImpl implements ModifyMapper {

    @Override
    public ModifyResponseTO userDEToResponse(UserDE user) {
        if ( user == null ) {
            return null;
        }

        ModifyResponseTO.ModifyResponseTOBuilder modifyResponseTO = ModifyResponseTO.builder();

        modifyResponseTO.username( user.getUsername() );
        modifyResponseTO.type( user.getType() );

        return modifyResponseTO.build();
    }
}
