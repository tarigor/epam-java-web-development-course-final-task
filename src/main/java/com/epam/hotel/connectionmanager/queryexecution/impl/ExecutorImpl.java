package com.epam.hotel.connectionmanager.queryexecution.impl;

import com.epam.hotel.connectionmanager.connectionpool.impl.ConnectionPoolImpl;
import com.epam.hotel.connectionmanager.queryexecution.Executor;
import com.epam.hotel.dao.BaseDao;
import com.epam.hotel.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Callable;

/**
 * Provides the functionality of the transaction to accessing a database.
 */
public class ExecutorImpl extends BaseDao implements Executor {
    private static final ExecutorImpl instance = new ExecutorImpl();
    private static final ConnectionPoolImpl connectionPool = ConnectionPoolImpl.getInstance();
    private static ThreadLocal<Connection> localConnectionThread;

    private ExecutorImpl() {
    }

    public static ExecutorImpl getInstance() {
        return instance;
    }

    public ExecutorImpl createConnection() throws DaoException {
        setConnection();
        localConnectionThread = new ThreadLocal<>();
        return this;
    }

    /**
     * Provides the functionality of the accessing to the database using transaction feature.
     *
     * @param callableDatabaseActionTask callable task to be processed as a transaction.
     * @param <T>                        type of the callable task.
     * @return result of the callable task.
     */
    @Override
    public <T> T executeAsTransaction(Callable<T> callableDatabaseActionTask) throws DaoException {
        T resultType = null;
        try {
            localConnectionThread.set(connection);
            resultType = callableDatabaseActionTask.call();
            connection.commit();
            return resultType;
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException sqlException) {
                    throw new DaoException(sqlException);
                }
            }
            throw new DaoException(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
                localConnectionThread.remove();
            }
        }
    }

    /**
     * Provides the functionality of the accessing to the database as a single query.
     *
     * @param callableDatabaseActionTask callable task to be processed as a transaction.
     * @param <T>                        type of the callable task.
     * @return result of the callable task.
     */
    @Override
    public <T> T executeAsSingleQuery(Callable<T> callableDatabaseActionTask) throws DaoException {
        T resultType = null;
        try {
            localConnectionThread.set(connection);
            resultType = callableDatabaseActionTask.call();
            connection.commit();
            return resultType;
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException sqlException) {
                    throw new DaoException(sqlException);
                }
            }
            throw new DaoException(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
                localConnectionThread.remove();
            }
        }
    }
}
