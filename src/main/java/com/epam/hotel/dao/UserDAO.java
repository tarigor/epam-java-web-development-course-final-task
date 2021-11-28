package com.epam.hotel.dao;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.entity.User;

import java.sql.Date;

/**
 * The interface of the {@link UserDAO} class.
 */
public interface UserDAO {
    long checkIfUserExist(int userHashCode);

    int insert(User user) throws DaoException;

    User get(long userHashCode);

    void insertRequest(long clientID, int persons, String roomClass, Date dateFrom, Date dateTo);

    double modifyAccount(long clientID, double chargeAmount);
}
