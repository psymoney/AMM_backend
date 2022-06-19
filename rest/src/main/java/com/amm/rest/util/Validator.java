package com.amm.rest.util;

import org.springframework.stereotype.Component;

@Component
public class Validator {
    public boolean isDateValid(String date) {
        if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return true;
        }
        return false;
    }

    public boolean isPeriodValid(String start, String end) {
        if (start.matches("\\d{4}-\\d{2}-\\d{2}") && end.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return true;
        }
        return false;
    }
}
