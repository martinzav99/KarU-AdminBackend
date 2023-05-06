package com.ungspp1.gadminbackend.api.signup.mapper;

import com.ungspp1.gadminbackend.api.signup.to.SignupUserRequestTO;
import com.ungspp1.gadminbackend.api.signup.to.SignupUserResponseTO;
import com.ungspp1.gadminbackend.model.entity.UserDE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SignupUserMapper {

    @Mapping(source = "email", target = "contactData.email")
    @Mapping(source = "phoneCode", target = "contactData.phoneCode")
    @Mapping(source = "phoneNumber", target = "contactData.phoneNumber")
    @Mapping(source = "street", target = "addressData.street")
    @Mapping(source = "streetNumber", target= "addressData.streetNumber")
    @Mapping(source = "city", target = "addressData.city")
    @Mapping(source = "state", target = "addressData.state")
    @Mapping(source = "zipCode", target = "addressData.zipCode")
    @Mapping(source = "country", target = "addressData.country")
    UserDE SignupUserRequestToDE(SignupUserRequestTO request);

    SignupUserResponseTO UserDEToResponse(UserDE user);
}
