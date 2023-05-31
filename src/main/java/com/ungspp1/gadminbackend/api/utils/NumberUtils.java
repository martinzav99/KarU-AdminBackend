package com.ungspp1.gadminbackend.api.utils;

import java.util.Random;

public class NumberUtils {
    
    public static String randomNumber() {
        int RandomNumber = new Random().nextInt(999999) + 1;
        return String.format("%06d", RandomNumber);
    }

    public static float toPercentage(float number){
        return (float) (number*0.01);
    }
    
}
