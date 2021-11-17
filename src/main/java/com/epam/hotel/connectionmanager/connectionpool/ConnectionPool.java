package com.epam.hotel.connectionmanager.connectionpool;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnectionFromPool();

    void releaseConnection(Connection connection);
}
