package com.epam.hotel.connectionmanager.queryexecution;

import com.epam.hotel.dao.exception.DaoException;

import java.util.concurrent.Callable;

/**
 * Provides methods to be implemented for transaction functionality.
 */
public interface Executor {
    <T> T executeAsTransaction(Callable<T> callableDatabaseActionTask) throws DaoException;

    <T> T executeAsSingleQuery(Callable<T> callableDatabaseActionTask) throws DaoException;
}
