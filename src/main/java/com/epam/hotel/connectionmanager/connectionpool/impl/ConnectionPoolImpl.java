package com.epam.hotel.connectionmanager.connectionpool.impl;

import com.epam.hotel.connectionmanager.connectionpool.ConnectionPool;
import com.epam.hotel.service.impl.DatabaseConnectionServiceImpl;
import com.epam.hotel.service.impl.PropertiesFileServiceImpl;
import com.epam.hotel.service.impl.ReusablePoolServiceImpl;
import org.apache.log4j.Logger;

import java.sql.Connection;

/**
 * The class provides the methods handling a connection pool.
 */
public class ConnectionPoolImpl implements ConnectionPool {
    private static final Logger logger = Logger.getLogger(ConnectionPoolImpl.class);
    private static final ConnectionPoolImpl instance = new ConnectionPoolImpl();
    private static final String DATABASE_PROPERTIES = "database.properties";
    private static final String CONNECTION_EXPIRATION_TIME = "connection.expiration.time";
    private ReusablePoolServiceImpl reusablePoolService;

    private ConnectionPoolImpl() {
    }

    public static ConnectionPoolImpl getInstance() {
        return instance;
    }

    /**
     * The method provides an initialization of the {@link ConnectionPoolImpl} and {@link DatabaseConnectionServiceImpl} classes.
     *
     * @param propertiesFileService     an instance of the {@link PropertiesFileServiceImpl} class.
     * @param databaseConnectionService an instance of the {@link DatabaseConnectionServiceImpl} class.
     * @param reusablePoolService       an instance of the {@link ReusablePoolServiceImpl} class.
     */
    public void init(PropertiesFileServiceImpl propertiesFileService, DatabaseConnectionServiceImpl databaseConnectionService, ReusablePoolServiceImpl reusablePoolService) {
        this.reusablePoolService = reusablePoolService;
        databaseConnectionService.init(propertiesFileService.getProperties(DATABASE_PROPERTIES));
        reusablePoolService.setDatabaseConnectionService(databaseConnectionService);
        reusablePoolService.setExpirationTime(Long.parseLong(propertiesFileService.getProperties(DATABASE_PROPERTIES).getProperty(CONNECTION_EXPIRATION_TIME)));
        logger.info("Connection Pool has been initialised");
    }

    /**
     * The method provides a connection getting from the connection pool.
     *
     * @return a connection instance.
     */
    @Override
    public Connection getConnectionFromPool() {
        return reusablePoolService.getConnection();
    }

    /**
     * The method provides the connection release after using of it.
     *
     * @param connection the connection instance will going to be released.
     */
    @Override
    public void releaseConnection(Connection connection) {
        reusablePoolService.releaseConnection(connection);
    }
}
