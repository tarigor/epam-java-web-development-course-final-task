package com.epam.hotel.service.impl;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.service.DatabaseConnectionService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Provides the functionality of connecting to a database.
 */
public class DatabaseConnectionServiceImpl implements DatabaseConnectionService {
    public static final String DATABASE_URL = "database.url";
    public static final String DATABASE_USER = "database.user";
    public static final String DATABASE_PASSWORD = "database.password";
    public static final String DATABASE_CLASS = "databaseClass";
    private static final Logger LOGGER = Logger.getLogger(DatabaseConnectionServiceImpl.class);
    private Properties databaseProperties;

    public DatabaseConnectionServiceImpl() {
    }

    /**
     * Registers a database class.
     *
     * @param databaseProperties an instance of {@link Properties} class.
     */
    public void init(Properties databaseProperties) {
        this.databaseProperties = databaseProperties;
        try {
            Class.forName(databaseProperties.getProperty(DATABASE_CLASS));
        } catch (ClassNotFoundException e) {
            LOGGER.error(e);
        }
        LOGGER.info("The database has been initialised");
    }

    /**
     * Gets an instance of a connection to the database.
     *
     * @return an instance of connection to the database.
     */
    @Override
    public Connection getConnection() throws DaoException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    databaseProperties.getProperty(DATABASE_URL),
                    databaseProperties.getProperty(DATABASE_USER),
                    databaseProperties.getProperty(DATABASE_PASSWORD));
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        LOGGER.info(String.format("The connection to database has been established with the following parameters: url=%s user=%s",
                databaseProperties.getProperty(DATABASE_URL),
                databaseProperties.getProperty(DATABASE_USER)));
        return connection;
    }

    /**
     * Destroys the used connection.
     *
     * @param connection an instance of the connection which is going to be destroyed.
     */
    @Override
    public void destroy(Connection connection) throws DaoException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
