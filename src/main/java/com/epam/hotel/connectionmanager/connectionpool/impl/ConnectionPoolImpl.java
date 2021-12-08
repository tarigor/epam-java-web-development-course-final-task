package com.epam.hotel.connectionmanager.connectionpool.impl;

import com.epam.hotel.connectionmanager.connectionpool.ConnectionPool;
import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.service.impl.DatabaseConnectionServiceImpl;
import com.epam.hotel.service.impl.PropertiesFileServiceImpl;
import com.epam.hotel.service.impl.ReusablePoolServiceImpl;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.Properties;

/**
 * Provides the functionality of the connection pool for accessing to database.
 */
public class ConnectionPoolImpl implements ConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(ConnectionPoolImpl.class);
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
     * Initializes of the {@link ConnectionPoolImpl} and {@link DatabaseConnectionServiceImpl} classes.
     *
     * @param propertiesFileService     an instance of the {@link PropertiesFileServiceImpl} class.
     * @param databaseConnectionService an instance of the {@link DatabaseConnectionServiceImpl} class.
     * @param reusablePoolService       an instance of the {@link ReusablePoolServiceImpl} class.
     */
    public void init(PropertiesFileServiceImpl propertiesFileService, DatabaseConnectionServiceImpl databaseConnectionService, ReusablePoolServiceImpl reusablePoolService) {
        this.reusablePoolService = reusablePoolService;
        Properties properties = propertiesFileService.getProperties(DATABASE_PROPERTIES);
        LOGGER.info(String.format("database.url -> %s", properties.getProperty("database.url")));
        databaseConnectionService.init(properties);
        reusablePoolService.setDatabaseConnectionService(databaseConnectionService);
        reusablePoolService.setExpirationTime(Long.parseLong(propertiesFileService.getProperties(DATABASE_PROPERTIES).getProperty(CONNECTION_EXPIRATION_TIME)));
        LOGGER.info("Connection Pool has been initialised");
    }

    public void initForTest(DatabaseConnectionServiceImpl databaseConnectionService, ReusablePoolServiceImpl reusablePoolService, Properties properties) {
        this.reusablePoolService = reusablePoolService;
        databaseConnectionService.init(properties);
        reusablePoolService.setDatabaseConnectionService(databaseConnectionService);
        reusablePoolService.setExpirationTime(Long.parseLong(properties.getProperty(CONNECTION_EXPIRATION_TIME)));
        LOGGER.info("Connection Pool has been initialised");
    }

    /**
     * Gets a new connection from the connection pool.
     *
     * @return an instance of the connection.
     */
    @Override
    public Connection getConnectionFromPool() throws DaoException {
        return reusablePoolService.getConnection();
    }

    /**
     * Releases the connection after using of it.
     *
     * @param connection which is going to be released.
     */
    @Override
    public void releaseConnection(Connection connection) {
        reusablePoolService.releaseConnection(connection);
    }
}
