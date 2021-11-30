package com.epam.hotel.service.factory;

import com.epam.hotel.service.exception.ServiceException;
import com.epam.hotel.utility.JsonFileHandler;

import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Provides the functionality of getting a specific service class based on factory pattern.
 */
public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final String SERVICE_JSON_FILE_PATH = "service.json";
    private final JsonFileHandler jsonFileHandler;
    private boolean exceptionWhileInit;
    private HashMap<ServiceType, Object> serviceObjectsMap;


    private ServiceFactory() {
        jsonFileHandler = new JsonFileHandler<String>(SERVICE_JSON_FILE_PATH);
    }

    public boolean isExceptionWhileInit() {
        return exceptionWhileInit;
    }

    public void setExceptionWhileInit(boolean exceptionWhileInit) {
        this.exceptionWhileInit = exceptionWhileInit;
    }

    public HashMap<ServiceType, Object> getServiceObjectsMap() {
        return serviceObjectsMap;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    /**
     * Gets an instance of the specific service class
     * class path of which to be taken from the json file for a specific service linked with {@link ServiceType} enum.
     *
     * @param service a enum as per which will be taken a service class path.
     * @return an instance of the selected service class.
     */
    public Object getService(ServiceType service) throws ServiceException {
        Class clazz = null;
        Object o = null;
        try {
            String className = jsonFileHandler.getMapOfCommandFromJson().get(service.name()).toString();
            clazz = Class.forName(className);
        } catch (NoSuchElementException | ClassNotFoundException e) {
            throw new ServiceException(e);
        }
        try {
            o = clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return o;
    }

    public ServiceFactory init() throws ServiceException {
        serviceObjectsMap = new HashMap<>();
        serviceObjectsMap.put(ServiceType.COMMON_SITE_ACTIVITY_SERVICE, this.getService(ServiceType.COMMON_SITE_ACTIVITY_SERVICE));
        serviceObjectsMap.put(ServiceType.CLIENT_SERVICE, this.getService(ServiceType.CLIENT_SERVICE));
        serviceObjectsMap.put(ServiceType.ROOM_SERVICE, this.getService(ServiceType.ROOM_SERVICE));
        serviceObjectsMap.put(ServiceType.BOOK_SERVICE, this.getService(ServiceType.BOOK_SERVICE));
        serviceObjectsMap.put(ServiceType.ADMIN_SERVICE, this.getService(ServiceType.ADMIN_SERVICE));
        serviceObjectsMap.put(ServiceType.DATABASE_CONNECTION_SERVICE, this.getService(ServiceType.DATABASE_CONNECTION_SERVICE));
        serviceObjectsMap.put(ServiceType.PROPERTIES_FILE_SERVICE, this.getService(ServiceType.PROPERTIES_FILE_SERVICE));
        serviceObjectsMap.put(ServiceType.REUSABLE_POOL_SERVICE, this.getService(ServiceType.REUSABLE_POOL_SERVICE));
        return this;
    }


}
