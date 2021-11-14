package com.epam.hotel.service;

import java.sql.Connection;

/**
 * The interface provides the methods to be implemented by of {@link com.epam.hotel.service.impl.ReusablePoolServiceImpl} class.
 */
public interface ReusablePoolService {
    Connection getConnection();

    void releaseConnection(Connection connection);
}
