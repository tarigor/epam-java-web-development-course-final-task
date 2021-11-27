package com.epam.hotel.service;

import com.epam.hotel.connectionmanager.transaction.impl.TransactionImpl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public abstract class BaseService {
    protected static final TransactionImpl transaction = TransactionImpl.getInstance();

    protected static Date convertStringToSqlDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateSQL = null;
        try {
            java.util.Date dateUtil = formatter.parse(date);
            dateSQL = new Date(dateUtil.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateSQL;
    }
}
