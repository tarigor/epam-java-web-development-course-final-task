package com.epam.hotel.dao;

import com.epam.hotel.connectionmanager.connectionpool.impl.ConnectionPoolImpl;
import com.epam.hotel.dao.exception.DaoException;

import java.sql.Connection;

public abstract class BaseDao {
    protected static Connection connection;

    protected void setConnection() throws DaoException {
        connection = ConnectionPoolImpl.getInstance().getConnectionFromPool();
    }
}
