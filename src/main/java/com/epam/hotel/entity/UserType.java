package com.epam.hotel.entity;

public enum UserType {
    ADMIN("Administrator"),
    CLIENT("Client");
    String userDescription;

    UserType(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getUserDescription() {
        return userDescription;
    }
}
