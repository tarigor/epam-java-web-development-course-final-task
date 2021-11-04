package com.epam.hotel.service.impl;

import com.epam.hotel.entity.HttpCommand;
import com.epam.hotel.service.Service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Class provides json file parsing into the HashMap by Gson library.
 *
 * @author Igor Taren
 */
public class JsonFileService implements Service {
    private final String JSON_FILE_PATH = ".//src//main//resources/commands/command.json";
    private final Gson gson;

    public JsonFileService() {
        gson = new Gson();
    }

    /**
     * Methods provides json file parsing into the HashMap.
     *
     * @return HashMap contains key/value of command name/http command instance)
     */
    public HashMap<String, HttpCommand> getMapOfCommandFromJson() {
        String fileContent = null;
        try {
            fileContent = new String(Files.readAllBytes(Paths.get(JSON_FILE_PATH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gson.fromJson(fileContent,
                new TypeToken<HashMap<String, HttpCommand>>() {
                }.getType());
    }

    @Override
    public void getServiceName() {

    }
}