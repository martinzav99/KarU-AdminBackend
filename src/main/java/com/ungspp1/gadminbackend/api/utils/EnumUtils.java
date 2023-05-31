package com.ungspp1.gadminbackend.api.utils;

import com.ungspp1.gadminbackend.model.enums.UserTypeEnum;
import com.ungspp1.gadminbackend.model.enums.VehicleStatusEnum;

public class EnumUtils {

    public static Boolean validateUserTypeEnum(String type) {
        Boolean found = false;
        UserTypeEnum[] types = UserTypeEnum.values();
        for(int i=0; i<types.length;i++){
            if(types[i].name().equals(type)){
                found=true;
                break;
            }
        }
        return found;
    } 

    public static Boolean validateVehicleStatusEnum(String status) {
        Boolean found = false;
        VehicleStatusEnum[] statusArray = VehicleStatusEnum.values();
        for(int i=0; i<statusArray.length;i++){
            if(statusArray[i].name().equals(status)){
                found=true;
                break;
            }
        }
        return found;
    } 

}
