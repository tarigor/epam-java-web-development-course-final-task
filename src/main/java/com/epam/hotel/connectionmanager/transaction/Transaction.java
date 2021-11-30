package com.epam.hotel.connectionmanager.transaction;

import com.epam.hotel.dao.exception.DaoException;

import java.util.concurrent.Callable;

/**
 * Provides methods to be implemented for transaction functionality.
 */
public interface Transaction {
    <T> T performTransaction(Callable<T> callableDatabaseActionTask) throws DaoException;
}
