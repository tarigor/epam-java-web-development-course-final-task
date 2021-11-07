package com.epam.hotel.controller.listener;

import com.epam.hotel.dao.connectionpool.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ConnectionPoolListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.getInstance().init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}