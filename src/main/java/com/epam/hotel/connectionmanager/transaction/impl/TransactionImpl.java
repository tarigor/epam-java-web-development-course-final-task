package com.epam.hotel.connectionmanager.transaction.impl;

import com.epam.hotel.connectionmanager.connectionpool.impl.ConnectionPoolImpl;
import com.epam.hotel.connectionmanager.transaction.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class TransactionImpl implements Transaction {
    private static final TransactionImpl instance = new TransactionImpl();
    private static final ThreadLocal<Connection> localConnectionThread = new ThreadLocal<>();
    private static final ConnectionPoolImpl connectionPool = ConnectionPoolImpl.getInstance();

    private TransactionImpl() {
    }

    public TransactionImpl getInstance() {
        return instance;
    }

    @Override
    public <T> T performTransaction(Callable<T> callableDatabaseActionTask) {
        Connection connection = connectionPool.getConnection();
        localConnectionThread.set(connection);
        T resultType = null;
        try {
            resultType = callableDatabaseActionTask.call();
            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connectionPool.releaseConnection(connection);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                localConnectionThread.remove();
            }
        }
        return resultType;
    }
}
