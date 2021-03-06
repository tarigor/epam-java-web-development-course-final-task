package com.epam.hotel.service;

import com.epam.hotel.connectionmanager.queryexecution.impl.ExecutorImpl;
import com.epam.hotel.service.exception.ServiceException;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BaseService {
    protected static final ExecutorImpl executor = ExecutorImpl.getInstance();

    protected static Date convertStringToSqlDate(String date) throws ServiceException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateSQL = null;
        try {
            java.util.Date dateUtil = formatter.parse(date);
            dateSQL = new Date(dateUtil.getTime());
        } catch (ParseException e) {
            throw new ServiceException(e);
        }
        return dateSQL;
    }
}
