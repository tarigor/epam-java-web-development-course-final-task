package com.epam.hotel.dao;

public interface CommonDAO<T> {
    void insert(T t);

    void update(T t);

    void delete(T t);

    T get(int id);
}
