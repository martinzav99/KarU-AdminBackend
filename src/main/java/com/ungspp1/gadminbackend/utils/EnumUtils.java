package com.ungspp1.gadminbackend.utils;

import com.ungspp1.gadminbackend.model.enums.FuelTypeEnum;
import com.ungspp1.gadminbackend.model.enums.UserTypeEnum;
import com.ungspp1.gadminbackend.model.enums.VehicleCategoryEnum;
import com.ungspp1.gadminbackend.model.enums.VehicleOriginEnum;
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

    public static Boolean validateFuelTypeEnum(String fuelType) {
        Boolean found = false;
        FuelTypeEnum[] fuelTypeArray = FuelTypeEnum.values();
        for(int i=0; i<fuelTypeArray.length;i++){
            if(fuelTypeArray[i].name().equals(fuelType)){
                found=true;
                break;
            }
        }
        return found;
    } 

    public static Boolean validateOriginEnum(String origin) {
        Boolean found = false;
        VehicleOriginEnum[] originArray = VehicleOriginEnum.values();
        for(int i=0; i<originArray.length;i++){
            if(originArray[i].name().equals(origin)){
                found=true;
                break;
            }
        }
        return found;
    } 

    public static Boolean validateVehicleCategoryEnum(String category) {
        Boolean found = false;
        VehicleCategoryEnum[] categoryArray = VehicleCategoryEnum.values();
        for(int i=0; i<categoryArray.length;i++){
            if(categoryArray[i].name().equals(category)){
                found=true;
                break;
            }
        }
        return found;
    } 

}
