package com.epam.hotel.dao;

import com.epam.hotel.dao.exception.DaoException;

import java.util.List;

/**
 * The class provides the set of the methods which are common for the all DAO classes.
 *
 * @param <T> the generic which can substituted by any DAO instance class.
 */
public interface CommonDAO<T> {
    int insert(T t) throws DaoException;

    void update(T t);

    void delete(T t);

    T get(int id);

    T get(String string);

    List get(T t) throws DaoException;
}
