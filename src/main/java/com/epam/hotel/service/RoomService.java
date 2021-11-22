package com.epam.hotel.service;

import com.epam.hotel.entity.RoomType;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface RoomService {
    HashMap<RoomType, ArrayList<Integer>> getFreeRooms(String dateFrom, String dateTo);
}
