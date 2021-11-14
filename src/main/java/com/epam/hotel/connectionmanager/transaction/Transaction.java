package com.epam.hotel.connectionmanager.transaction;

import java.util.concurrent.Callable;

public interface Transaction {
    <T> T performTransaction(Callable<T> callableDatabaseActionTask);
}
