package com.epam.hotel.service.impl;

import com.epam.hotel.service.DatabaseConnectionService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionServiceImpl implements DatabaseConnectionService {
    public static final String DATABASE_URL = "database.url";
    public static final String DATABASE_USER = "database.user";
    public static final String DATABASE_PASSWORD = "database.password";
    public static final String DATABASE_CLASS = "databaseClass";
    private static Logger logger = Logger.getLogger(DatabaseConnectionServiceImpl.class);
    private Properties databaseProperties;

    public DatabaseConnectionServiceImpl() {
    }

    public void init(Properties databaseProperties) {
        this.databaseProperties = databaseProperties;
        try {
            Class.forName(databaseProperties.getProperty(DATABASE_CLASS));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        logger.info("The database has been initialised");
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    databaseProperties.getProperty(DATABASE_URL),
                    databaseProperties.getProperty(DATABASE_USER),
                    databaseProperties.getProperty(DATABASE_PASSWORD));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info(String.format("The connection to database has been established with the following parameters: url=%s user=%s",
                databaseProperties.getProperty(DATABASE_URL),
                databaseProperties.getProperty(DATABASE_USER)));
        return connection;
    }

    @Override
    public void destroy(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
