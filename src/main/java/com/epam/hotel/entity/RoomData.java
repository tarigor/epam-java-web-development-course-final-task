package com.epam.hotel.entity;

import com.epam.hotel.types.RoomClass;

public class RoomData {
    double cost;
    private int persons;
    private RoomClass roomClass;

    public RoomData(int persons, RoomClass roomClass, double cost) {
        this.persons = persons;
        this.roomClass = roomClass;
        this.cost = cost;
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

    @Override
    public String toString() {
        return "RoomData{" +
                "persons=" + persons +
                ", roomClass=" + roomClass +
                ", cost=" + cost +
                '}';
    }
}
