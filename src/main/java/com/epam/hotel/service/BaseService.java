package com.epam.hotel.service;

import com.epam.hotel.connectionmanager.transaction.impl.TransactionImpl;

public abstract class BaseService {
    protected static final TransactionImpl transaction = TransactionImpl.getInstance();
}
