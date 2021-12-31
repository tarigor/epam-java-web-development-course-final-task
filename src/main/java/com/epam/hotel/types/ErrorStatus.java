package com.epam.hotel.types;

public enum ErrorStatus {
    ERROR_404("error.404"),
    ERROR_500("error.500"),
    ERROR_401("error.401");
    private String description;

    ErrorStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
