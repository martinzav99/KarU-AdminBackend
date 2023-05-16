package com.ungspp1.gadminbackend.api.user.mapper;

import com.ungspp1.gadminbackend.api.user.to.UserTO;
import com.ungspp1.gadminbackend.model.entity.AddressDE;
import com.ungspp1.gadminbackend.model.entity.ContactDE;
import com.ungspp1.gadminbackend.model.entity.UserDE;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-16T17:58:23-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserTO userDEToTO(UserDE user) {
        if ( user == null ) {
            return null;
        }

        UserTO.UserTOBuilder userTO = UserTO.builder();

        userTO.email( userContactDataEmail( user ) );
        userTO.phoneCode( userContactDataPhoneCode( user ) );
        userTO.phoneNumber( userContactDataPhoneNumber( user ) );
        userTO.street( userAddressDataStreet( user ) );
        userTO.streetNumber( userAddressDataStreetNumber( user ) );
        userTO.city( userAddressDataCity( user ) );
        userTO.state( userAddressDataState( user ) );
        userTO.zipCode( userAddressDataZipCode( user ) );
        userTO.country( userAddressDataCountry( user ) );
        userTO.fullName( user.getFullName() );
        userTO.document( user.getDocument() );
        userTO.username( user.getUsername() );
        userTO.type( user.getType() );
        userTO.branch( user.getBranch() );
        userTO.technicalLevel( user.getTechnicalLevel() );

        return userTO.build();
    }

    @Override
    public List<UserTO> userDEListToTO(List<UserDE> userList) {
        if ( userList == null ) {
            return null;
        }

        List<UserTO> list = new ArrayList<UserTO>( userList.size() );
        for ( UserDE userDE : userList ) {
            list.add( userDEToTO( userDE ) );
        }

        return list;
    }

    private String userContactDataEmail(UserDE userDE) {
        if ( userDE == null ) {
            return null;
        }
        ContactDE contactData = userDE.getContactData();
        if ( contactData == null ) {
            return null;
        }
        String email = contactData.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }

    private String userContactDataPhoneCode(UserDE userDE) {
        if ( userDE == null ) {
            return null;
        }
        ContactDE contactData = userDE.getContactData();
        if ( contactData == null ) {
            return null;
        }
        String phoneCode = contactData.getPhoneCode();
        if ( phoneCode == null ) {
            return null;
        }
        return phoneCode;
    }

    private String userContactDataPhoneNumber(UserDE userDE) {
        if ( userDE == null ) {
            return null;
        }
        ContactDE contactData = userDE.getContactData();
        if ( contactData == null ) {
            return null;
        }
        String phoneNumber = contactData.getPhoneNumber();
        if ( phoneNumber == null ) {
            return null;
        }
        return phoneNumber;
    }

    private String userAddressDataStreet(UserDE userDE) {
        if ( userDE == null ) {
            return null;
        }
        AddressDE addressData = userDE.getAddressData();
        if ( addressData == null ) {
            return null;
        }
        String street = addressData.getStreet();
        if ( street == null ) {
            return null;
        }
        return street;
    }

    private String userAddressDataStreetNumber(UserDE userDE) {
        if ( userDE == null ) {
            return null;
        }
        AddressDE addressData = userDE.getAddressData();
        if ( addressData == null ) {
            return null;
        }
        String streetNumber = addressData.getStreetNumber();
        if ( streetNumber == null ) {
            return null;
        }
        return streetNumber;
    }

    private String userAddressDataCity(UserDE userDE) {
        if ( userDE == null ) {
            return null;
        }
        AddressDE addressData = userDE.getAddressData();
        if ( addressData == null ) {
            return null;
        }
        String city = addressData.getCity();
        if ( city == null ) {
            return null;
        }
        return city;
    }

    private String userAddressDataState(UserDE userDE) {
        if ( userDE == null ) {
            return null;
        }
        AddressDE addressData = userDE.getAddressData();
        if ( addressData == null ) {
            return null;
        }
        String state = addressData.getState();
        if ( state == null ) {
            return null;
        }
        return state;
    }

    private String userAddressDataZipCode(UserDE userDE) {
        if ( userDE == null ) {
            return null;
        }
        AddressDE addressData = userDE.getAddressData();
        if ( addressData == null ) {
            return null;
        }
        String zipCode = addressData.getZipCode();
        if ( zipCode == null ) {
            return null;
        }
        return zipCode;
    }

    private String userAddressDataCountry(UserDE userDE) {
        if ( userDE == null ) {
            return null;
        }
        AddressDE addressData = userDE.getAddressData();
        if ( addressData == null ) {
            return null;
        }
        String country = addressData.getCountry();
        if ( country == null ) {
            return null;
        }
        return country;
    }
}
