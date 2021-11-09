package com.epam.hotel.dao.factory;

import com.epam.hotel.service.Service;
import com.epam.hotel.utility.JsonFileHandler;

import java.util.NoSuchElementException;

public class DAOService {
    private static final DAOService instance = new DAOService();
    private final String DAO_JSON_FILE_PATH = "dao.json";
    private final JsonFileHandler jsonFileHandler;

    private DAOService() {
        jsonFileHandler = new JsonFileHandler<String>(DAO_JSON_FILE_PATH);
    }

    public static DAOService getInstance() {
        return instance;
    }

    public Object getService(Service service) {
        Class clazz = null;
        Object object = null;
        try {
            String className = jsonFileHandler.getMapOfCommandFromJson().get(service.name()).toString();
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
