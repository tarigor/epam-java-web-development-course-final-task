package com.epam.hotel.command.factory;

import com.epam.hotel.entity.CommandJsonFile;
import com.epam.hotel.service.exception.ServiceException;
import com.epam.hotel.utility.JsonFileHandler;
import com.google.gson.internal.LinkedTreeMap;

import java.util.NoSuchElementException;

/**
 * Provides the functionality of getting a specific command class based on factory pattern.
 */
public class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();
    public static final String CLAZZ = "clazz";
    private final String COMMAND_JSON_FILE_PATH = "command.json";
    private final JsonFileHandler jsonFileHandler;

    private CommandFactory() {
        jsonFileHandler = new JsonFileHandler<CommandJsonFile>(COMMAND_JSON_FILE_PATH);
    }

    public static CommandFactory getInstance() {
        return instance;
    }

    /**
     * Provides a command getting from the command factory.
     *
     * @param command String name of the command.
     * @return class provides a command implementation.
     */
    public Class getCommand(String command) throws ServiceException {
        Class clazz = null;
        try {
            String className = ((LinkedTreeMap) jsonFileHandler.getMapOfCommandFromJson().get(command)).get(CLAZZ).toString();
            clazz = Class.forName(className);
        } catch (NoSuchElementException | ClassNotFoundException | ServiceException e) {
            throw new ServiceException(e);
        }
        return clazz;
    }

    /**
     * Methods provides a getting of the role type from json file of the specific command.
     *
     * @param command String name of the command.
     * @return a role of the specific command.
     */
    public String getCommandRole(String command) throws ServiceException {
        return ((LinkedTreeMap) jsonFileHandler.getMapOfCommandFromJson().get(command)).get("role").toString();
    }
}
