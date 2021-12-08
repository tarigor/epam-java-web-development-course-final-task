package com.epam.hotel.dao.factory;

import com.epam.hotel.service.exception.ServiceException;
import com.epam.hotel.utility.JsonFileHandler;

import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Provides the functionality of getting a specific DAO class based on factory pattern.
 */
public class DAOServiceFactory {
    private static final DAOServiceFactory instance = new DAOServiceFactory();
    private final String DAO_JSON_FILE_PATH = "dao.json";
    private final JsonFileHandler<String> jsonFileHandler;
    private HashMap<DAOType, Object> daoObjectMap;

    private DAOServiceFactory() {
        jsonFileHandler = new JsonFileHandler(DAO_JSON_FILE_PATH);
    }

    public static DAOServiceFactory getInstance() {
        return instance;
    }

    public HashMap<DAOType, Object> getDaoObjectMap() {
        return daoObjectMap;
    }

    /**
     * Gets a specific DAO class from the DAO factory.
     *
     * @param dao the specific dao name form the {@link DAOType} enumeration.
     * @return an instance of the specific DAO class.
     */
    public Object getDAO(DAOType dao) throws ServiceException {

        Class clazz = null;
        Object object = null;
        try {
            String className = jsonFileHandler.getMapOfCommandFromJson().get(dao.name());
            clazz = Class.forName(className);
        } catch (NoSuchElementException | ClassNotFoundException e) {
            throw new ServiceException(e);
        }
        try {
            object = clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new ServiceException(e);
        }
        return object;
    }

    public DAOServiceFactory init() throws ServiceException {
        daoObjectMap = new HashMap<>();
        daoObjectMap.put(DAOType.USER_DAO, this.getDAO(DAOType.USER_DAO));
        daoObjectMap.put(DAOType.CLIENT_ORDER_DAO, this.getDAO(DAOType.CLIENT_ORDER_DAO));
        daoObjectMap.put(DAOType.ROOM_DAO, this.getDAO(DAOType.ROOM_DAO));
        daoObjectMap.put(DAOType.ORDER_DAO, this.getDAO(DAOType.ORDER_DAO));
        daoObjectMap.put(DAOType.REQUEST_DAO, this.getDAO(DAOType.REQUEST_DAO));
        return this;
    }
}
