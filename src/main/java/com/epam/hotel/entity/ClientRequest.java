package com.epam.hotel.entity;

import com.epam.hotel.types.OrderStatus;

import java.sql.Date;

public class ClientRequest {
    private long requestID;
    private long clientID;
    private String firstName;
    private String lastName;
    private String email;
    private int persons;
    private String roomClass;
    private Date dateFrom;
    private Date dateTo;
    private OrderStatus requestStatus;
    private boolean processed;

    public ClientRequest(long requestID, int persons, String roomClass, Date dateFrom, Date dateTo, OrderStatus requestStatus) {
        this.requestID = requestID;
        this.persons = persons;
        this.roomClass = roomClass;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.requestStatus = requestStatus;
        if (requestStatus.equals(OrderStatus.WAITING_FOR_APPROVAL)) {
            setProcessed(true);
        }
    }

    public ClientRequest(int persons, String roomClass, Date dateFrom, Date dateTo) {
        this.persons = persons;
        this.roomClass = roomClass;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public ClientRequest(long requestID, long clientID, String firstName, String lastName, String email, int persons, String roomClass, Date dateFrom, Date dateTo, OrderStatus requestStatus) {
        this.requestID = requestID;
        this.clientID = clientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.persons = persons;
        this.roomClass = roomClass;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.requestStatus = requestStatus;
        if (requestStatus.equals(OrderStatus.WAITING_FOR_APPROVAL)) {
            setProcessed(true);
        }
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public long getClientID() {
        return clientID;
    }

    public void setClientID(long clientID) {
        this.clientID = clientID;
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

    public OrderStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(OrderStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public long getRequestID() {
        return requestID;
    }

    public void setRequestID(long requestID) {
        this.requestID = requestID;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        return "ClientRequest{" +
                "requestID=" + requestID +
                ", persons=" + persons +
                ", roomClass='" + roomClass + '\'' +
                ", dateFrom='" + dateFrom + '\'' +
                ", dateTo='" + dateTo + '\'' +
                ", requestStatus='" + requestStatus + '\'' +
                '}';
    }
}
