package com.epam.hotel.utility;

import com.epam.hotel.service.factory.ServiceFactory;
import com.epam.hotel.service.factory.ServiceType;
import com.epam.hotel.service.impl.PropertiesFileServiceImpl;

import java.util.regex.Pattern;

public enum InputRegex {
    /**
     * Input text requirements
     * Only contains alphanumeric characters, underscore and dot.
     * Underscore and dot can't be at the end or start of a username (e.g _username / username_ / .username / username.).
     * Underscore and dot can't be next to each other (e.g user_.name).
     * Underscore or dot can't be used multiple times in a row (e.g user__name / user..name).
     * Number of characters must be between 8 to 20.
     */
    NAME("^[a-zA-Z]+([._]?[a-zA-Z]+)*$",
            ((PropertiesFileServiceImpl) ServiceFactory
                    .getInstance()
                    .getService(ServiceType.PROPERTIES_FILE_SERVICE))
                    .getProperties("local.properties")
                    .getProperty("wrong.user.name.input")),

    /**
     * Secure Password requirements
     * <p>
     * Password must contain at least one digit [0-9].
     * Password must contain at least one lowercase Latin character [a-z].
     * Password must contain at least one uppercase Latin character [A-Z].
     * Password must contain at least one special character like ! @ # & ( ).
     * Password must contain a length of at least 8 characters and a maximum of 20 characters.
     **/
    PASSWORD("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$2",
            ((PropertiesFileServiceImpl) ServiceFactory
                    .getInstance()
                    .getService(ServiceType.PROPERTIES_FILE_SERVICE))
                    .getProperties("local.properties")
                    .getProperty("wrong.user.password.input"));
    String regexExpression;
    String description;

    InputRegex(String regexExpression, String description) {
        this.regexExpression = regexExpression;
        this.description  =description;
    }

    public static String getDescription(InputRegex inputRegex) {
        return inputRegex.description;
    }

    public Pattern getPattern(InputRegex inputRegex) {
        return Pattern.compile(inputRegex.regexExpression);
    }
}
