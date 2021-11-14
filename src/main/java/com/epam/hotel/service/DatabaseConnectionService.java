package com.epam.hotel.service;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The interface provides the methods to be implemented by of {@link com.epam.hotel.service.impl.DatabaseConnectionServiceImpl} class.
 */
public interface DatabaseConnectionService {
    Connection getConnection() throws SQLException;

    void destroy(Connection connection);
}
