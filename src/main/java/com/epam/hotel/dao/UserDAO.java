package com.epam.hotel.dao;

import com.epam.hotel.entity.User;

import java.util.List;

public interface UserDAO extends CommonDAO{
    List<User> getAllUsers();
}
