package com.epam.hotel.service;

import com.epam.hotel.entity.Room;
import com.epam.hotel.entity.RoomData;
import com.epam.hotel.service.exception.ServiceException;
import com.epam.hotel.service.impl.RoomServiceImpl;

import java.util.ArrayList;

/**
 * Provides methods to be implemented in {@link com.epam.hotel.service.impl.RoomServiceImpl} class.
 * See description of the methods implemented:
 *
 * @see com.epam.hotel.service.impl.RoomServiceImpl#getFreeRooms(String, String)
 * @see com.epam.hotel.service.impl.RoomServiceImpl#getRoomPrice(int)
 * @see RoomServiceImpl#getRoomsData()
 */
public interface RoomService {
    ArrayList<Room> getFreeRooms(String dateFrom, String dateTo) throws ServiceException;

    double getRoomPrice(int roomID) throws ServiceException;

    ArrayList<RoomData> getRoomsData() throws ServiceException;
}
