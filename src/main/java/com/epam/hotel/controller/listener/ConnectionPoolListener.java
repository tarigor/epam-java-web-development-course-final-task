package com.epam.hotel.controller.listener;

import com.epam.hotel.connectionmanager.connectionpool.impl.ConnectionPoolImpl;
import com.epam.hotel.service.factory.ServiceFactory;
import com.epam.hotel.service.factory.ServiceType;
import com.epam.hotel.service.impl.DatabaseConnectionServiceImpl;
import com.epam.hotel.service.impl.PropertiesFileServiceImpl;
import com.epam.hotel.service.impl.ReusablePoolServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * The class provides the methods which are instantiates the services classes:
 * - {@link DatabaseConnectionServiceImpl} service class;
 * - {@link PropertiesFileServiceImpl} service class;
 * - {@link ReusablePoolServiceImpl} service class.
 * and initializes the {@link ConnectionPoolImpl} class.
 */
public class ConnectionPoolListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DatabaseConnectionServiceImpl databaseConnectionService = (DatabaseConnectionServiceImpl) ServiceFactory.getInstance().getService(ServiceType.DATABASE_CONNECTION_SERVICE);
        PropertiesFileServiceImpl propertiesFileService = (PropertiesFileServiceImpl) ServiceFactory.getInstance().getService(ServiceType.PROPERTIES_FILE_SERVICE);
        ReusablePoolServiceImpl reusablePoolService = (ReusablePoolServiceImpl) ServiceFactory.getInstance().getService(ServiceType.REUSABLE_POOL_SERVICE);
        ConnectionPoolImpl.getInstance().init(propertiesFileService, databaseConnectionService, reusablePoolService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}