package com.epam.hotel.connectionmanager.transaction.impl;

import com.epam.hotel.connectionmanager.connectionpool.impl.ConnectionPoolImpl;
import com.epam.hotel.connectionmanager.transaction.Transaction;
import com.epam.hotel.dao.BaseDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class TransactionImpl extends BaseDao implements Transaction {
    private static final TransactionImpl instance = new TransactionImpl();
    private static final ConnectionPoolImpl connectionPool = ConnectionPoolImpl.getInstance();
    private static ThreadLocal<Connection> localConnectionThread;

    private TransactionImpl() {
    }

    public static TransactionImpl getInstance() {
        return instance;
    }

    public TransactionImpl createConnection() {
        setConnection();
        localConnectionThread = new ThreadLocal<>();
        return this;
    }

    @Override
    public <T> T performTransaction(Callable<T> callableDatabaseActionTask) {
        T resultType = null;
        try {
            localConnectionThread.set(connection);
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

    public void performTransaction(Runnable callableDatabaseActionTask) {
        try {
            localConnectionThread.set(connection);
            callableDatabaseActionTask.run();
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
    }
}
