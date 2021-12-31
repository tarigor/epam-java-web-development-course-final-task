package com.epam.hotel.dao;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.dao.impl.RoomDAOImpl;
import com.epam.hotel.entity.Room;
import com.epam.hotel.entity.RoomData;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Provides methods to be implemented in {@link RoomDAOImpl} class.
 * See description of the methods implemented:
 *
 * @see RoomDAOImpl#getFreeRooms(Date, Date)
 * @see RoomDAOImpl#getRoomPrice(int)
 * @see RoomDAOImpl#getRoomsData()
 */
public interface IRoomDAO {

    ArrayList<Room> getFreeRooms(Date dateFrom, Date dateTo) throws DaoException;

    double getRoomPrice(int roomID) throws DaoException;

    ArrayList<RoomData> getRoomsData() throws DaoException;
}
