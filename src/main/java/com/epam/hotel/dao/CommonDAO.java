package com.epam.hotel.dao;

/**
 * The class provides the set of the methods which are common for the all DAO classes.
 *
 * @param <T> the generic which can substituted by any DAO instance class.
 */
public interface CommonDAO<T> {
    void insert(T t);

    void update(T t);

    void delete(T t);

    T get(int id);
}
