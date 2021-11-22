package com.epam.hotel.entity;

public enum OrderStatus {
    APPROVED_AND_BOOKED("client.cabinet.approved"),
    WAITING_FOR_APPROVAL("client.cabinet.waiting");
    private String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
