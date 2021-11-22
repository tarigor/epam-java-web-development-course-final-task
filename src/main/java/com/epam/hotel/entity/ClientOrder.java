package com.epam.hotel.entity;

import java.util.Date;

public class ClientOrder {
    private long orderID;
    private boolean canBeCanceled;
    private int roomID;
    private String roomClass;
    private int personsAmount;
    private Date checkInDate;
    private Date checkOutDate;
    private OrderStatus orderStatus;


    public ClientOrder(long orderID, int roomID, String roomClass, int personsAmount, Date checkInDate, Date checkOutDate, OrderStatus orderStatus) {
        this.orderID = orderID;
        this.roomID = roomID;
        this.roomClass = roomClass;
        this.personsAmount = personsAmount;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.orderStatus = orderStatus;
        if (orderStatus.equals(OrderStatus.WAITING_FOR_APPROVAL)) {
            setCanBeCanceled(true);
        }
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public boolean isCanBeCanceled() {
        return canBeCanceled;
    }

    public void setCanBeCanceled(boolean canBeCanceled) {
        this.canBeCanceled = canBeCanceled;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    public int getPersonsAmount() {
        return personsAmount;
    }

    public void setPersonsAmount(int personsAmount) {
        this.personsAmount = personsAmount;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "ClientOrder{" +
                "orderID=" + orderID +
                ", canBeCanceled=" + canBeCanceled +
                ", roomID=" + roomID +
                ", roomClass='" + roomClass + '\'' +
                ", personsAmount=" + personsAmount +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
