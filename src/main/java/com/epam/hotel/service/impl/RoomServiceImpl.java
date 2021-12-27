package com.epam.hotel.service.impl;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.dao.factory.DAOServiceFactory;
import com.epam.hotel.dao.factory.DAOType;
import com.epam.hotel.dao.impl.RoomDAOImpl;
import com.epam.hotel.entity.Room;
import com.epam.hotel.entity.RoomData;
import com.epam.hotel.service.BaseService;
import com.epam.hotel.service.RoomService;
import com.epam.hotel.service.exception.ServiceException;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Provides the functionality during manipulation with a rooms data.
 */
public class RoomServiceImpl extends BaseService implements RoomService {

    private final RoomDAOImpl roomDAO = (RoomDAOImpl) DAOServiceFactory.getInstance().getDaoObjectMap().get(DAOType.ROOM_DAO);

    /**
     * Gets a free(available) rooms from the database.
     *
     * @param dateFrom a date from which the room availability is checked.
     * @param dateTo   a date till which the room availability is checked.
     * @return a list of the available rooms.
     */
    @Override
    public ArrayList<Room> getFreeRooms(String dateFrom, String dateTo) throws ServiceException {
        Date dateFromSQL = convertStringToSqlDate(dateFrom);
        Date dateToSQL = convertStringToSqlDate(dateTo);
        try {
            ArrayList<Room> roomsList = executor.createConnection().executeAsSingleQuery(() -> roomDAO.getFreeRooms(dateFromSQL, dateToSQL));
            return roomsList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Gets a room price of the specific room.
     *
     * @param roomID a room ID.
     * @return a price of room.
     */
    @Override
    public double getRoomPrice(int roomID) throws ServiceException {
        try {
            return executor.createConnection().executeAsSingleQuery(() -> roomDAO.getRoomPrice(roomID));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    /**
     * Gets a room datas of all rooms.
     *
     * @return a list of rooms data.
     */
    @Override
    public ArrayList<RoomData> getRoomsData() throws ServiceException {
        try {
            return executor.createConnection().executeAsSingleQuery(() -> roomDAO.getRoomsData());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
