package com.epam.hotel.connectionmanager.connectionpool;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection();

    void releaseConnection(Connection connection);
}
