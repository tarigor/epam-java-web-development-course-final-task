package com.epam.hotel.command.factory;

import com.epam.hotel.entity.CommandJsonFile;
import com.epam.hotel.utility.JsonFileHandler;
import com.google.gson.internal.LinkedTreeMap;

import java.util.NoSuchElementException;

/**
 * The class provides a factory of command receiving vi HTTP from the web pages.
 * The class implemented based on singleton pattern.
 */
public class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();
    private final String COMMAND_JSON_FILE_PATH = "command.json";
    private final JsonFileHandler jsonFileHandler;

    private CommandFactory() {
        jsonFileHandler = new JsonFileHandler<CommandJsonFile>(COMMAND_JSON_FILE_PATH);
    }

    public static CommandFactory getInstance() {
        return instance;
    }

    /**
     * The method provides a command getting from the command factory.
     *
     * @param command String name of the command.
     * @return A class provided a command implementation.
     */
    public Class getCommand(String command) {
        Class clazz = null;
        try {
            String className = ((LinkedTreeMap) jsonFileHandler.getMapOfCommandFromJson().get(command)).get("clazz").toString();
            clazz = Class.forName(className);
        } catch (NoSuchElementException | ClassNotFoundException e) {
            e.fillInStackTrace();
        }
        return clazz;
    }
}
