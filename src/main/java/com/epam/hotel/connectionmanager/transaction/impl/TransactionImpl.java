package com.epam.hotel.connectionmanager.transaction.impl;

import com.epam.hotel.connectionmanager.connectionpool.impl.ConnectionPoolImpl;
import com.epam.hotel.connectionmanager.transaction.Transaction;
import com.epam.hotel.dao.BaseDao;
import com.epam.hotel.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Callable;

/**
 * Provides the functionality of the transaction to accessing a database.
 */
public class TransactionImpl extends BaseDao implements Transaction {
    private static final TransactionImpl instance = new TransactionImpl();
    private static final ConnectionPoolImpl connectionPool = ConnectionPoolImpl.getInstance();
    private static ThreadLocal<Connection> localConnectionThread;

    private TransactionImpl() {
    }

    public static TransactionImpl getInstance() {
        return instance;
    }

    public TransactionImpl createConnection() throws DaoException {
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
    public <T> T performTransaction(Callable<T> callableDatabaseActionTask) throws DaoException {
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
     * Provides overloaded version of the {@link TransactionImpl#performTransaction(Runnable)} method and gets a runnable task as an input.
     *
     * @param runnableDatabaseActionTask runnable task to be processed as a transaction.
     */
    public void performTransaction(Runnable runnableDatabaseActionTask) throws DaoException {
        try {
            localConnectionThread.set(connection);
            runnableDatabaseActionTask.run();
            connection.commit();
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
     * Provides overloaded version of the {@link TransactionImpl#performTransaction(Runnable)} method and gets a runnable task as an input.
     *
     * @param runnableDatabaseActionTask runnable task to be processed as a transaction.
     */
    public void performTransactionTest(Runnable runnableDatabaseActionTask) throws DaoException {
        try {
            localConnectionThread.set(connection);
            runnableDatabaseActionTask.run();
            connection.commit();
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
