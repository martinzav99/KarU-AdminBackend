package com.ungspp1.gadminbackend.api.utils;

import java.util.Random;

public class NumberUtils {
    
    public static String RandomNumber() {
        int RandomNumber = new Random().nextInt(999999) + 1;
        return String.format("%06d", RandomNumber);
     }
    
}
