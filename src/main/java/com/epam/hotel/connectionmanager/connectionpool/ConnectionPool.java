package com.epam.hotel.connectionmanager.connectionpool;

import com.epam.hotel.service.impl.DatabaseConnectionServiceImpl;
import com.epam.hotel.service.impl.PropertiesFileServiceImpl;
import com.epam.hotel.service.impl.ReusablePoolServiceImpl;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class ConnectionPool {
    private static final Logger logger = Logger.getLogger(ConnectionPool.class);
    private static final ConnectionPool instance = new ConnectionPool();
    private static final String DATABASE_PROPERTIES = "database.properties";
    private static final String CONNECTION_EXPIRATION_TIME = "connection.expiration.time";
    private ReusablePoolServiceImpl reusablePoolService;

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public void init(PropertiesFileServiceImpl propertiesFileService, DatabaseConnectionServiceImpl databaseConnectionService, ReusablePoolServiceImpl reusablePoolService) {
        this.reusablePoolService = reusablePoolService;
        databaseConnectionService.init(propertiesFileService.getProperties(DATABASE_PROPERTIES));
        reusablePoolService.setDatabaseConnectionService(databaseConnectionService);
        reusablePoolService.setExpirationTime(Long.parseLong(propertiesFileService.getProperties(DATABASE_PROPERTIES).getProperty(CONNECTION_EXPIRATION_TIME)));
        logger.info("Connection Pool has been initialised");
    }

    public Connection getConnection() {
        return reusablePoolService.getConnection();
    }

    public void releaseConnection(Connection connection) {
        reusablePoolService.releaseConnection(connection);
    }
}
