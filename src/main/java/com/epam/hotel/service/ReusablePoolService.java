package com.epam.hotel.service;

import java.sql.Connection;

public interface ReusablePoolService {
    Connection getConnection();

    void releaseConnection(Connection connection);
}
