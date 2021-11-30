package com.epam.hotel.controller.listener;

import com.epam.hotel.connectionmanager.connectionpool.impl.ConnectionPoolImpl;
import com.epam.hotel.dao.factory.DAOServiceFactory;
import com.epam.hotel.menu.impl.SiteMenuServiceImpl;
import com.epam.hotel.service.exception.ServiceException;
import com.epam.hotel.service.factory.ServiceFactory;
import com.epam.hotel.service.factory.ServiceType;
import com.epam.hotel.service.impl.DatabaseConnectionServiceImpl;
import com.epam.hotel.service.impl.PropertiesFileServiceImpl;
import com.epam.hotel.service.impl.ReusablePoolServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;

/**
 * Provides an instantiation of the services classes:
 * - {@link DatabaseConnectionServiceImpl} service class;
 * - {@link PropertiesFileServiceImpl} service class;
 * - {@link ReusablePoolServiceImpl} service class.
 * and initialization of the {@link ConnectionPoolImpl} class.
 */
public class InitListener implements ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(InitListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            DAOServiceFactory.getInstance().init();
            ServiceFactory.getInstance().init();
            SiteMenuServiceImpl.getInstance().init();
            DatabaseConnectionServiceImpl databaseConnectionService = (DatabaseConnectionServiceImpl) ServiceFactory.getInstance().getServiceObjectsMap().get(ServiceType.DATABASE_CONNECTION_SERVICE);
            PropertiesFileServiceImpl propertiesFileService = (PropertiesFileServiceImpl) ServiceFactory.getInstance().getServiceObjectsMap().get(ServiceType.PROPERTIES_FILE_SERVICE);
            ReusablePoolServiceImpl reusablePoolService = (ReusablePoolServiceImpl) ServiceFactory.getInstance().getServiceObjectsMap().get(ServiceType.REUSABLE_POOL_SERVICE);
            ConnectionPoolImpl.getInstance().init(propertiesFileService, databaseConnectionService, reusablePoolService);
        } catch (ServiceException e) {
            ServiceFactory.getInstance().setExceptionWhileInit(true);
            LOGGER.error(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}