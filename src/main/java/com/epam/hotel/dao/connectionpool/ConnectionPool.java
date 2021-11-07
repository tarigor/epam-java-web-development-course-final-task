package com.epam.hotel.dao.connectionpool;

import com.epam.hotel.service.factory.ServiceFactory;
import com.epam.hotel.service.impl.DatabaseConnectionServiceImpl;
import com.epam.hotel.service.impl.PropertiesFileServiceImpl;
import com.epam.hotel.service.impl.ReusablePoolImpl;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class ConnectionPool {
    private static final ConnectionPool instance = new ConnectionPool();
    private static final String DATABASE_PROPERTIES = "database.properties";
    private static final String CONNECTION_EXPIRATION_TIME = "connection.expiration.time";
    private static Logger logger = Logger.getLogger(ConnectionPool.class);
    private PropertiesFileServiceImpl propertiesFileService;
    private DatabaseConnectionServiceImpl databaseConnectionService;
    private ReusablePoolImpl reusablePool;

    private ConnectionPool() {
        setDatabaseConnectionService(ServiceFactory.getInstance().getDatabaseConnectionService());
        setPropertiesFileService(ServiceFactory.getInstance().getPropertiesFileService());
        setReusablePool(ServiceFactory.getInstance().getReusablePool());
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    private void setPropertiesFileService(PropertiesFileServiceImpl propertiesFileService) {
        this.propertiesFileService = propertiesFileService;
    }

    private void setDatabaseConnectionService(DatabaseConnectionServiceImpl databaseConnectionService) {
        this.databaseConnectionService = databaseConnectionService;
    }

    private void setReusablePool(ReusablePoolImpl reusablePool) {
        this.reusablePool = reusablePool;
    }

    public void init() {
        databaseConnectionService.init(propertiesFileService.getProperties(DATABASE_PROPERTIES));
        reusablePool.setDatabaseConnectionService(databaseConnectionService);
        reusablePool.setExpirationTime(Long.parseLong(propertiesFileService.getProperties(DATABASE_PROPERTIES).getProperty(CONNECTION_EXPIRATION_TIME)));
        logger.info("Connection Pool has been initialised");
    }

    public Connection getConnection() {
        return reusablePool.getConnection();
    }

    public void releaseConnection(Connection connection) {
        reusablePool.releaseConnection(connection);
    }
}
