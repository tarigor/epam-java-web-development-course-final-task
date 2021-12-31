package com.epam.hotel.service;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.service.impl.ReusablePoolServiceImpl;

import java.sql.Connection;

/**
 * Provides methods to be implemented in {@link com.epam.hotel.service.impl.ReusablePoolServiceImpl} class.
 * See description of the methods implemented:
 *
 * @see ReusablePoolServiceImpl#getConnection()
 * @see com.epam.hotel.service.impl.ReusablePoolServiceImpl#releaseConnection(Connection)
 */
public interface IReusablePoolService {
    Connection getConnection() throws DaoException;

    void releaseConnection(Connection connection);
}
