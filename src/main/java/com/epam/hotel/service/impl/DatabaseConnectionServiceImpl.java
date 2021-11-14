package com.epam.hotel.service.impl;

import com.epam.hotel.service.DatabaseConnectionService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The class provides the methods of the database connection handling.
 */
public class DatabaseConnectionServiceImpl implements DatabaseConnectionService {
    public static final String DATABASE_URL = "database.url";
    public static final String DATABASE_USER = "database.user";
    public static final String DATABASE_PASSWORD = "database.password";
    public static final String DATABASE_CLASS = "databaseClass";
    private static Logger logger = Logger.getLogger(DatabaseConnectionServiceImpl.class);
    private Properties databaseProperties;

    public DatabaseConnectionServiceImpl() {
    }

    /**
     * The method is registering of the database class.
     *
     * @param databaseProperties the key of the database properties file.
     */
    public void init(Properties databaseProperties) {
        this.databaseProperties = databaseProperties;
        try {
            Class.forName(databaseProperties.getProperty(DATABASE_CLASS));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        logger.info("The database has been initialised");
    }

    /**
     * The method gets an instance of the connection to the database.
     *
     * @return an instance of connection to the database.
     */
    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    databaseProperties.getProperty(DATABASE_URL),
                    databaseProperties.getProperty(DATABASE_USER),
                    databaseProperties.getProperty(DATABASE_PASSWORD));
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info(String.format("The connection to database has been established with the following parameters: url=%s user=%s",
                databaseProperties.getProperty(DATABASE_URL),
                databaseProperties.getProperty(DATABASE_USER)));
        return connection;
    }

    /**
     * The methods providing a destroying of the used connection.
     *
     * @param connection an instance of the connection which is going to be destroyed.
     */
    @Override
    public void destroy(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
