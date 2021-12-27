package com.epam.hotel.entity;

import com.epam.hotel.types.RoomClass;

public class RoomData {
    private double cost;
    private int persons;
    private RoomClass roomClass;
    private String roomImageLink;

    public RoomData(int persons, RoomClass roomClass, double cost, String roomImageLink) {
        this.persons = persons;
        this.roomClass = roomClass;
        this.cost = cost;
        this.roomImageLink = roomImageLink;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public RoomClass getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(RoomClass roomClass) {
        this.roomClass = roomClass;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getRoomImageLink() {
        return roomImageLink;
    }

    public void setRoomImageLink(String roomImageLink) {
        this.roomImageLink = roomImageLink;
    }

    @Override
    public String toString() {
        return "RoomData{" +
                "cost=" + cost +
                ", persons=" + persons +
                ", roomClass=" + roomClass +
                ", roomImageLink='" + roomImageLink + '\'' +
                '}';
    }
}
