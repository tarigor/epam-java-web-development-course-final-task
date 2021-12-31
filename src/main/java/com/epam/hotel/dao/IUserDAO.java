package com.epam.hotel.dao;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.dao.impl.UserDAOImpl;
import com.epam.hotel.entity.User;

/**
 * Provides methods to be implemented in {@link UserDAOImpl} class.
 * See description of the methods implemented:
 *
 * @see UserDAOImpl#checkIfUserExist(long)
 * @see UserDAOImpl#insert(User)
 * @see UserDAOImpl#get(long)
 * @see UserDAOImpl#topUpAccount(long, double)
 */
public interface IUserDAO {
    long checkIfUserExist(long userHashCode) throws DaoException;

    int insert(User user) throws DaoException;

    User get(long userHashCode) throws DaoException;

    double topUpAccount(long clientID, double chargeAmount) throws DaoException;
}
