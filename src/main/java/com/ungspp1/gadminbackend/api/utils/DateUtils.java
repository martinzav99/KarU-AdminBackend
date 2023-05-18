package com.ungspp1.gadminbackend.api.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateUtils {

    static public long minutesBetweenDates(LocalDateTime firstDate, LocalDateTime lastDate){
        return ChronoUnit.MINUTES.between(firstDate, lastDate);
    }

}
    
