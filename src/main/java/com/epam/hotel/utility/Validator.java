package com.epam.hotel.utility;

import java.util.HashMap;
import java.util.regex.Matcher;

/**
 * Provides the functionality of a data validation inputted on a web page by a user.
 */
public class Validator {
    private final HashMap<String, String> map;

    public Validator() {
        map = new HashMap<>();
    }

    /**
     * Checks an input as per specific regex pattern.
     *
     * @param inputRegex a regex pattern.
     * @param text       a text to be checked.
     * @return a boolean result of check.
     */
    public static boolean checkInput(InputRegex inputRegex, String text) {
        Matcher matcher = inputRegex.getPattern(inputRegex).matcher(text);
        return matcher.matches();
    }

    public HashMap<String, String> getMap() {
        return map;
    }

    /**
     * Validates a data.
     *
     * @param paramName    a parameter name.
     * @param paramContent a content of the parameter.
     * @param regex        a regex type.
     * @return a boolean result of a validation.
     */
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

    /**
     * Validates an inputted password a twice.
     *
     * @param password         an inputted password.
     * @param repeatedPassword a second time an inputted password.
     * @return a boolean result of the passwords comparision.
     */
    public boolean validatePasswordTwice(String password, String repeatedPassword) {
        return password.equals(repeatedPassword);
    }
}
