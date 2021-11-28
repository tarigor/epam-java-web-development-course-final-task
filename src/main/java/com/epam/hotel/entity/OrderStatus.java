package com.epam.hotel.entity;

public enum OrderStatus {
    APPROVED_WAITING_FOR_PAYMENT("client.cabinet.approved"),
    WAITING_FOR_APPROVAL("client.cabinet.waiting"),
    REJECTED("client.cabinet.rejected"),
    PAID_AND_BOOKED("client.cabinet.paid"),
    REQUEST_PROCESSED("client.cabinet.processed");
    private String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
