package com.epam.hotel.dao;

import com.epam.hotel.entity.User;

import java.util.List;

/**
 * The interface of the {@link UserDAO} class.
 */
public interface UserDAO extends CommonDAO {
    List<User> getAllUsers();
}
