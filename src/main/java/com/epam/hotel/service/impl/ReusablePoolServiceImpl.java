package com.epam.hotel.service.impl;

import com.epam.hotel.service.ReusablePoolService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;

public class ReusablePoolServiceImpl implements ReusablePoolService {
    private final Logger logger = Logger.getLogger(ReusablePoolServiceImpl.class);
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
        logger.info(String.format("ReusablePoolImpl -> the expiration time has been set: %dms", expirationTime));
    }

    public void setDatabaseConnectionService(DatabaseConnectionServiceImpl databaseConnectionService) {
        this.databaseConnectionService = databaseConnectionService;
        logger.info("ReusablePoolImpl -> databaseConnectionService has been assigned");
    }

    @Override
    public synchronized Connection getConnection() {
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
                        return (connection);
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

    private boolean checkIfConnectionIsUsing(Connection connection) {
        try {
            return (!connection.isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    @Override
    public synchronized void releaseConnection(Connection connection) {
        usedConnections.remove(connection);
        freeConnections.put(connection, System.currentTimeMillis());
    }

}
