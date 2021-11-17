package com.epam.hotel.dao.factory;

import com.epam.hotel.utility.JsonFileHandler;

import java.util.NoSuchElementException;

/**
 * The class provides the methods for gets DAO from the DAO factory.
 */
public class DAOServiceFactory {
    private static final DAOServiceFactory instance = new DAOServiceFactory();
    private final String DAO_JSON_FILE_PATH = "dao.json";
    private final JsonFileHandler jsonFileHandler;

    private DAOServiceFactory() {
        jsonFileHandler = new JsonFileHandler<String>(DAO_JSON_FILE_PATH);
    }

    public static DAOServiceFactory getInstance() {
        return instance;
    }

    /**
     * The method provides a certain DAO class getting from the DAO factory.
     *
     * @param dao the certain dao name form the {@link DAOType} enumeration.
     * @return an instance of the certain DAO class.
     */
    public Object getDAO(DAOType dao) {

        Class clazz = null;
        Object object = null;
        try {
            String className = jsonFileHandler.getMapOfCommandFromJson().get(dao.name()).toString();
            clazz = Class.forName(className);
        } catch (NoSuchElementException | ClassNotFoundException e) {
            e.fillInStackTrace();
        }
        try {
            object = clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return object;
    }
}
