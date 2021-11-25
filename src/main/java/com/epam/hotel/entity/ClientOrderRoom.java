package com.epam.hotel.entity;

import java.util.Date;

public class ClientOrderRoom {
    private long orderID;
    private boolean canBeCanceled;
    private String firstName;
    private String lastName;
    private String email;
    private int roomID;
    private String roomClass;
    private Date checkInDate;
    private Date checkOutDate;
    private OrderStatus orderStatus;


    public ClientOrderRoom(long orderID, int roomID, String roomClass, Date checkInDate, Date checkOutDate, OrderStatus orderStatus) {
        this.orderID = orderID;
        this.roomID = roomID;
        this.roomClass = roomClass;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.orderStatus = orderStatus;
        if (orderStatus.equals(OrderStatus.WAITING_FOR_APPROVAL)) {
            setCanBeCanceled(true);
        }
    }

    public ClientOrderRoom(long orderID, String firstName, String lastName, String email, int roomID, String roomClass, Date checkInDate, Date checkOutDate, OrderStatus orderStatus) {
        this.orderID = orderID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roomID = roomID;
        this.roomClass = roomClass;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.orderStatus = orderStatus;
        if (orderStatus.equals(OrderStatus.WAITING_FOR_APPROVAL)) {
            setCanBeCanceled(true);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", orderStatus=" + orderStatus +
                '}';
    }

    public String toStringShort() {
        return "ClientOrder{" +
                "  roomID=" + roomID +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
