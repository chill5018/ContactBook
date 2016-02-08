package com.peak2peak.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Created by colinhill on 2/8/16.
 */
public class DateUtil {

    private  static final String DATE_PATTERN ="dd.MM.yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static String format(LocalDate date){
        if (date == null){
            return null;
        }

        return DATE_FORMATTER.format(date);
    }

    public static LocalDate parse(String dateString){
        try {
         return  DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e){
            return null;
        }
    }

    public static boolean validDate(String date){
        return DateUtil.parse(date) != null;
    }
}
