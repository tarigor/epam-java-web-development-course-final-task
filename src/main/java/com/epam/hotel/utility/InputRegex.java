package com.epam.hotel.utility;

import java.util.regex.Pattern;

/**
 * Provides an ENUMs of the available regex patterns during an input check on a web page.
 */
public enum InputRegex {
    /**
     * Input text requirements
     * Must contain only letters.
     * Start with a capital letter.
     * The number of letters is at least 2.
     */
    NAME("^[A-Z][a-z]{1,18}$","validator.wrong.user.name.input"),
    /**
     * Secure Password requirements
     * <p>
     * Password must contain at least one digit [0-9].
     * Password must contain at least one lowercase Latin character [a-z].
     * Password must contain at least one uppercase Latin character [A-Z].
     * Password must contain at least one special character like ! @ # & ( ).
     * Password must contain a length of at least 6 characters.
     **/
    PASSWORD("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+!=])(?=\\S+$).{4,}$","validator.wrong.user.password.input"),
    EMAIL("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,56}","validator.wrong.user.email.input"),
    PASSWORD_DOUBLE_CHECK("","validator.wrong.user.password.double.check.input");
    String regexExpression;
    String description;

    InputRegex(String regexExpression, String description) {
        this.regexExpression = regexExpression;
        this.description = description;
    }

    public static String getDescription(InputRegex inputRegex) {
        return inputRegex.description;
    }

    public Pattern getPattern(InputRegex inputRegex) {
        return Pattern.compile(inputRegex.regexExpression);
    }

    private static class Constants {
        public static final String PROPERTIES = "local/local.properties";
    }
}
