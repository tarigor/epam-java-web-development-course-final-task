package com.epam.hotel.dao;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.entity.ClientOrderRoom;
import com.epam.hotel.entity.User;

import java.util.ArrayList;

public interface ClientOrderDAO extends CommonDAO<User> {

    @Override
    ArrayList<ClientOrderRoom> get(User user) throws DaoException;

    void deleteRecord(int order_id, int room_id) throws DaoException;
}
