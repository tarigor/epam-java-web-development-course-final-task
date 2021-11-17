package com.epam.hotel.dao;

import com.epam.hotel.connectionmanager.connectionpool.impl.ConnectionPoolImpl;

import java.sql.Connection;

public abstract class BaseDao {
    protected static Connection connection;

    protected void setConnection() {
        connection = ConnectionPoolImpl.getInstance().getConnectionFromPool();
    }
}
