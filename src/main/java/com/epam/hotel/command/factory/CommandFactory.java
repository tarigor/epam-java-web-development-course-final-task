package com.epam.hotel.command.factory;

import com.epam.hotel.entity.CommandJsonFile;
import com.epam.hotel.utility.JsonFileHandler;
import com.google.gson.internal.LinkedTreeMap;

import java.util.NoSuchElementException;

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
