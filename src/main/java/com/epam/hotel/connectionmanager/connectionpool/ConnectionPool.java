package com.epam.hotel.connectionmanager.connectionpool;

import com.epam.hotel.dao.exception.DaoException;

import java.sql.Connection;

/**
 * Provides methods to be implemented for connection pool functionality.
 */
public interface ConnectionPool {
    Connection getConnectionFromPool() throws DaoException;

    void releaseConnection(Connection connection);
}
