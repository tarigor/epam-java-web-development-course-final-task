package com.epam.hotel.dao;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.entity.User;

import java.util.List;

/**
 * The interface of the {@link UserDAO} class.
 */
public interface UserDAO extends CommonDAO<User> {
    List<User> getAllUsers();

    long checkIfUserExist(int userHashCode);

    @Override
    User get(String email);

    @Override
    int insert(User user) throws DaoException;
}
