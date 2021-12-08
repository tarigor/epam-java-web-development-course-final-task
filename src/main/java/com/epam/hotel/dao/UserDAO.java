package com.epam.hotel.dao;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.entity.User;

/**
 * Provides methods to be implemented in {@link com.epam.hotel.dao.impl.UserDAOImpl} class.
 * See description of the methods implemented:
 *
 * @see com.epam.hotel.dao.impl.UserDAOImpl#checkIfUserExist(long)
 * @see com.epam.hotel.dao.impl.UserDAOImpl#insert(User)
 * @see com.epam.hotel.dao.impl.UserDAOImpl#get(long)
 * @see com.epam.hotel.dao.impl.UserDAOImpl#topUpAccount(long, double)
 */
public interface UserDAO {
    long checkIfUserExist(long userHashCode) throws DaoException;

    int insert(User user) throws DaoException;

    User get(long userHashCode) throws DaoException;

    double topUpAccount(long clientID, double chargeAmount) throws DaoException;
}
