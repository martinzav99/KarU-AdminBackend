package com.ungspp1.gadminbackend.api.user.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ungspp1.gadminbackend.api.user.to.UserTO;
import com.ungspp1.gadminbackend.model.entity.UserDE;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "email", source = "contactData.email")
    @Mapping(target = "phoneCode", source = "contactData.phoneCode")
    @Mapping(target = "phoneNumber", source = "contactData.phoneNumber")
    @Mapping(target = "street", source = "addressData.street")
    @Mapping(target = "streetNumber", source= "addressData.streetNumber")
    @Mapping(target = "city", source = "addressData.city")
    @Mapping(target = "state", source = "addressData.state")
    @Mapping(target = "zipCode", source = "addressData.zipCode")
    @Mapping(target = "country", source = "addressData.country")
    UserTO userDEToTO(UserDE user);

    List<UserTO> userDEListToTO(List<UserDE> userList);

}
