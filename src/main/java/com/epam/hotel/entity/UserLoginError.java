package com.epam.hotel.entity;

public class UserLoginError {
    private static String type;

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        UserLoginError.type = type;
    }
}
