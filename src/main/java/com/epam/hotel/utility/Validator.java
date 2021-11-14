package com.epam.hotel.utility;

import java.util.regex.Matcher;

public class Validator {

    public static boolean checkInput(InputRegex inputRegex, String text) {
        Matcher matcher = inputRegex.getPattern(inputRegex).matcher(text);
        return !matcher.matches();
    }
}
