package com.epam.hotel.controller.listener;

import com.epam.hotel.connectionmanager.connectionpool.ConnectionPool;
import com.epam.hotel.service.Service;
import com.epam.hotel.service.factory.ServiceFactory;
import com.epam.hotel.service.impl.DatabaseConnectionServiceImpl;
import com.epam.hotel.service.impl.PropertiesFileServiceImpl;
import com.epam.hotel.service.impl.ReusablePoolServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ConnectionPoolListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DatabaseConnectionServiceImpl databaseConnectionService = (DatabaseConnectionServiceImpl) ServiceFactory.getInstance().getService(Service.DATABASE_CONNECTION_SERVICE);
        PropertiesFileServiceImpl propertiesFileService = (PropertiesFileServiceImpl) ServiceFactory.getInstance().getService(Service.PROPERTIES_FILE_SERVICE);
        ReusablePoolServiceImpl reusablePoolService = (ReusablePoolServiceImpl) ServiceFactory.getInstance().getService(Service.REUSABLE_POOL_SERVICE);

        ConnectionPool.getInstance().init(propertiesFileService,databaseConnectionService,reusablePoolService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}