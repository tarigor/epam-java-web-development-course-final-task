package com.epam.hotel.utility;

import java.util.HashMap;
import java.util.regex.Matcher;

public class Validator {
    private HashMap<String, String> map;

    public HashMap<String, String> getMap() {
        return map;
    }

    public Validator() {
        map = new HashMap<>();
    }

    public static boolean checkInput(InputRegex inputRegex, String text) {
        Matcher matcher = inputRegex.getPattern(inputRegex).matcher(text);
        return matcher.matches();
    }

    public boolean validate(String[] paramName, String[] paramContent, InputRegex[] regex) {
        boolean result;
        boolean atLeastOneFalse = true;
        for (int i = 0; i < paramContent.length; i++) {
            result = Validator.checkInput(regex[i], paramContent[i]);
            if (!result) {
                map.put(paramName[i], InputRegex.getDescription(regex[i]));
                atLeastOneFalse = false;
            }
        }
        return atLeastOneFalse;
    }


    public boolean validateDoublePassword(String password, String repeatedPassword) {
        return password.equals(repeatedPassword);
    }
}
