package com.epam.hotel.dao;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.entity.User;

import java.sql.Date;

/**
 * Provides methods to be implemented in {@link com.epam.hotel.dao.impl.UserDAOImpl} class.
 * See description of the methods implemented:
 *
 * @see com.epam.hotel.dao.impl.UserDAOImpl#checkIfUserExist(int)
 * @see com.epam.hotel.dao.impl.UserDAOImpl#insert(User)
 * @see com.epam.hotel.dao.impl.UserDAOImpl#get(long)
 * @see com.epam.hotel.dao.impl.UserDAOImpl#insertRequest(long, int, String, Date, Date)
 * @see com.epam.hotel.dao.impl.UserDAOImpl#modifyAccount(long, double)
 */
public interface UserDAO {
    long checkIfUserExist(int userHashCode) throws DaoException;

    int insert(User user) throws DaoException;

    User get(long userHashCode) throws DaoException;

    int insertRequest(long clientID, int persons, String roomClass, Date dateFrom, Date dateTo) throws DaoException;

    double modifyAccount(long clientID, double chargeAmount) throws DaoException;
}
