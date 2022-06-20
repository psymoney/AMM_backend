package com.amm.rest.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class Validator {
    public boolean isDateValid(String date) {
        if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return true;
        }
        return false;
    }

    public boolean isPeriodValid(String start, String end) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (!format.parse(start).before(format.parse(end))) {
            return false;
        }

        if (start.matches("\\d{4}-\\d{2}-\\d{2}") && end.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return true;
        }
        return false;
    }
}
