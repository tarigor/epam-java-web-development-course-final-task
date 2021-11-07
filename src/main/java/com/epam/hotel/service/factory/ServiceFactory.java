package com.epam.hotel.service.factory;

import com.epam.hotel.service.impl.DatabaseConnectionServiceImpl;
import com.epam.hotel.service.impl.JsonFileServiceImpl;
import com.epam.hotel.service.impl.PropertiesFileServiceImpl;
import com.epam.hotel.service.impl.ReusablePoolImpl;
import org.apache.log4j.Logger;


public class ServiceFactory {
    private static Logger logger = Logger.getLogger(ServiceFactory.class);
    private static final ServiceFactory instance = new ServiceFactory();
    private final JsonFileServiceImpl jsonFileService;
    private final PropertiesFileServiceImpl propertiesFileService;
    private final DatabaseConnectionServiceImpl databaseConnectionService;
    private final ReusablePoolImpl reusablePool;

    private ServiceFactory() {
        jsonFileService = new JsonFileServiceImpl();
        propertiesFileService = new PropertiesFileServiceImpl();
        databaseConnectionService = new DatabaseConnectionServiceImpl();
        reusablePool = new ReusablePoolImpl();
        logger.info("the service factory has been initialised");
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public JsonFileServiceImpl getJsonFileService() {
        return jsonFileService;
    }

    public PropertiesFileServiceImpl getPropertiesFileService() {
        return propertiesFileService;
    }

    public DatabaseConnectionServiceImpl getDatabaseConnectionService() {
        return databaseConnectionService;
    }

    public ReusablePoolImpl getReusablePool() {
        return reusablePool;
    }
}
