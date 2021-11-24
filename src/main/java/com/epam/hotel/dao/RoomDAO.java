package com.epam.hotel.dao;

import com.epam.hotel.entity.Room;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public interface RoomDAO {
//    List getRoomsWithinRange(Date dateFrom, Date dateTo);
    ArrayList<Room> getFreeRooms(Date dateFrom, Date dateTo);
}
