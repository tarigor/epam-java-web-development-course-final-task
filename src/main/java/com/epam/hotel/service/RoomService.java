package com.epam.hotel.service;

import com.epam.hotel.entity.Room;

import java.util.ArrayList;

public interface RoomService {
    ArrayList<Room> getFreeRooms(String dateFrom, String dateTo);
    double getRoomPrice(int roomID);
}
