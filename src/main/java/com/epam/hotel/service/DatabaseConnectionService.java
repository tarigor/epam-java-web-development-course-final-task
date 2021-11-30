package com.epam.hotel.service;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.service.impl.DatabaseConnectionServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Provides methods to be implemented in {@link com.epam.hotel.service.impl.DatabaseConnectionServiceImpl} class.
 * See description of the methods implemented:
 *
 * @see DatabaseConnectionServiceImpl#getConnection()
 * @see com.epam.hotel.service.impl.DatabaseConnectionServiceImpl#destroy(Connection)
 */
public interface DatabaseConnectionService {
    Connection getConnection() throws SQLException, DaoException;

    void destroy(Connection connection) throws DaoException;
}
