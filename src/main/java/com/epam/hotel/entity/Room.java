package com.epam.hotel.entity;

public class Room {
    private String roomType;
    private String roomID;

    public Room() {
    }

    public Room(String roomType, String roomID) {
        this.roomType = roomType;
        this.roomID = roomID;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomType='" + roomType + '\'' +
                ", roomID='" + roomID + '\'' +
                '}';
    }
}
