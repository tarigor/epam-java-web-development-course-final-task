package com.epam.hotel.service;

import java.sql.Connection;

public interface ReusablePool {
    Connection getConnection();

    void releaseConnection(Connection connection);
}
