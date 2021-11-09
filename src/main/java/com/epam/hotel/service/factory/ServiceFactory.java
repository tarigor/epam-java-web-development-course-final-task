package com.epam.hotel.service.factory;

import com.epam.hotel.service.Service;
import com.epam.hotel.utility.JsonFileHandler;

import java.util.NoSuchElementException;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final String SERVICE_JSON_FILE_PATH = "service.json";
    private final JsonFileHandler jsonFileHandler;

    private ServiceFactory() {
        jsonFileHandler = new JsonFileHandler<String>(SERVICE_JSON_FILE_PATH);
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public Object getService(Service service) {
        Class clazz = null;
        Object o = null;
        try {
            String className = jsonFileHandler.getMapOfCommandFromJson().get(service.name()).toString();
            clazz = Class.forName(className);
        } catch (NoSuchElementException | ClassNotFoundException e) {
            e.fillInStackTrace();
        }
        try {
            o = clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return o;
    }
}
