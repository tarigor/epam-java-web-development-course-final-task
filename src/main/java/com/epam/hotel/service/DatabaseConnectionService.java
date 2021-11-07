package com.epam.hotel.service;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnectionService {
    Connection getConnection() throws SQLException;

    void destroy(Connection connection);
}
