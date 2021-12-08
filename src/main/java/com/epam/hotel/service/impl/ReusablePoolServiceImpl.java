package com.epam.hotel.service.impl;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.service.ReusablePoolService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Provides the functionality to access a database using a connection pool.
 */
public class ReusablePoolServiceImpl implements ReusablePoolService {
    private static final Logger LOGGER = Logger.getLogger(ReusablePoolServiceImpl.class);
    private final Hashtable<Connection, Long> usedConnections;
    private final Hashtable<Connection, Long> freeConnections;
    private long expirationTime;
    private DatabaseConnectionServiceImpl databaseConnectionService;

    public ReusablePoolServiceImpl() {
        usedConnections = new Hashtable<Connection, Long>();
        freeConnections = new Hashtable<Connection, Long>();
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
        LOGGER.info(String.format("ReusablePoolImpl -> the expiration time has been set: %dms", expirationTime));
    }

    public void setDatabaseConnectionService(DatabaseConnectionServiceImpl databaseConnectionService) {
        this.databaseConnectionService = databaseConnectionService;
        LOGGER.info("ReusablePoolImpl -> databaseConnectionService has been assigned");
    }

    /**
     * Gets a single connection from the connection pool.
     *
     * @return an instance of the connection from the connection pool.
     */
    @Override
    public synchronized Connection getConnection() throws DaoException {
        long now = System.currentTimeMillis();
        Connection connection;
        if (freeConnections.size() > 0) {
            Enumeration<Connection> e = freeConnections.keys();
            while (e.hasMoreElements()) {
                connection = e.nextElement();
                if ((now - freeConnections.get(connection)) > expirationTime) {
                    // object has expired
                    freeConnections.remove(connection);
                    databaseConnectionService.destroy(connection);
                    connection = null;
                } else {
                    if (checkIfConnectionIsUsing(connection)) {
                        freeConnections.remove(connection);
                        usedConnections.put(connection, now);
                        LOGGER.info(String.format("Amount of the connections in a Pool of the Connections -> %d", usedConnections.size()));
                        return connection;
                    } else {
                        // object failed validation
                        freeConnections.remove(connection);
                        databaseConnectionService.destroy(connection);
                        connection = null;
                    }
                }
            }
        }
        // no objects available, create a new one
        connection = databaseConnectionService.getConnection();
        usedConnections.put(connection, now);
        return connection;
    }

    /**
     * Checks that the connection is not closed now.
     *
     * @param connection an instance of the connection which is going to be checked for close state.
     * @return usage test result.
     */
    private boolean checkIfConnectionIsUsing(Connection connection) throws DaoException {
        try {
            return (!connection.isClosed());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Releases a specific connection after usage of it.
     *
     * @param connection an instance of the connection which is going to be released.
     */
    @Override
    public synchronized void releaseConnection(Connection connection) {
        usedConnections.remove(connection);
        freeConnections.put(connection, System.currentTimeMillis());
    }

}
