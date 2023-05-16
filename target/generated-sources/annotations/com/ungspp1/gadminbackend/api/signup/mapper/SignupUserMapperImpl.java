package com.ungspp1.gadminbackend.api.signup.mapper;

import com.ungspp1.gadminbackend.api.signup.to.SignupUserRequestTO;
import com.ungspp1.gadminbackend.api.signup.to.SignupUserResponseTO;
import com.ungspp1.gadminbackend.model.entity.AddressDE;
import com.ungspp1.gadminbackend.model.entity.ContactDE;
import com.ungspp1.gadminbackend.model.entity.UserDE;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-16T17:58:24-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class SignupUserMapperImpl implements SignupUserMapper {

    @Override
    public UserDE SignupUserRequestToDE(SignupUserRequestTO request) {
        if ( request == null ) {
            return null;
        }

        UserDE.UserDEBuilder userDE = UserDE.builder();

        userDE.contactData( signupUserRequestTOToContactDE( request ) );
        userDE.addressData( signupUserRequestTOToAddressDE( request ) );
        userDE.fullName( request.getFullName() );
        userDE.document( request.getDocument() );
        userDE.username( request.getUsername() );
        userDE.password( request.getPassword() );
        userDE.type( request.getType() );
        userDE.branch( request.getBranch() );
        userDE.technicalLevel( request.getTechnicalLevel() );

        return userDE.build();
    }

    @Override
    public SignupUserResponseTO UserDEToResponse(UserDE user) {
        if ( user == null ) {
            return null;
        }

        SignupUserResponseTO.SignupUserResponseTOBuilder signupUserResponseTO = SignupUserResponseTO.builder();

        signupUserResponseTO.username( user.getUsername() );
        signupUserResponseTO.type( user.getType() );

        return signupUserResponseTO.build();
    }

    protected ContactDE signupUserRequestTOToContactDE(SignupUserRequestTO signupUserRequestTO) {
        if ( signupUserRequestTO == null ) {
            return null;
        }

        ContactDE.ContactDEBuilder contactDE = ContactDE.builder();

        contactDE.email( signupUserRequestTO.getEmail() );
        contactDE.phoneCode( signupUserRequestTO.getPhoneCode() );
        contactDE.phoneNumber( signupUserRequestTO.getPhoneNumber() );

        return contactDE.build();
    }

    protected AddressDE signupUserRequestTOToAddressDE(SignupUserRequestTO signupUserRequestTO) {
        if ( signupUserRequestTO == null ) {
            return null;
        }

        AddressDE.AddressDEBuilder addressDE = AddressDE.builder();

        addressDE.street( signupUserRequestTO.getStreet() );
        addressDE.streetNumber( signupUserRequestTO.getStreetNumber() );
        addressDE.city( signupUserRequestTO.getCity() );
        addressDE.state( signupUserRequestTO.getState() );
        addressDE.zipCode( signupUserRequestTO.getZipCode() );
        addressDE.country( signupUserRequestTO.getCountry() );

        return addressDE.build();
    }
}
