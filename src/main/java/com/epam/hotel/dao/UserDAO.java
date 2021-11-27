package com.epam.hotel.dao;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.entity.User;

import java.sql.Date;
import java.util.List;

/**
 * The interface of the {@link UserDAO} class.
 */
public interface UserDAO {
    long checkIfUserExist(int userHashCode);
    int insert(User user) throws DaoException;
    User get(int userHashCode);
    void insertRequest(long clientID, int persons, String roomClass, Date dateFrom, Date dateTo);
}
