package com.epam.hotel.utility;

import com.epam.hotel.service.exception.ServiceException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Provides the functionality of getting data from a json file.
 *
 * @param <T>
 */
public class JsonFileHandler<T> {
    private final Gson gson;
    private final String jsonFilePath;

    public JsonFileHandler(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
        gson = new Gson();
    }

    /**
     * Parses a json file into the HashMap.
     *
     * @return a hashmap contains a result of a json file parsing.
     */
    public HashMap<String, T> getMapOfCommandFromJson() throws ServiceException {
        // for tomcat run
        String rootPath = Thread.currentThread().getContextClassLoader().
                getResource("").getPath().
                replace("/out/artifacts/epam_java_web_development_course_final_task_war_exploded/WEB-INF/classes/", "/src/main/resources/factory/");
        //for test
//        String rootPath = Thread.currentThread().getContextClassLoader().
//                getResource("").getPath().replace("/target/test-classes/", "/src/main/resources/factory/");
        String fileContent = null;
        try {
            fileContent = new String(Files.readAllBytes(Paths.get(rootPath + jsonFilePath)));
        } catch (IOException e) {
            throw new ServiceException(e);
        }
        return gson.fromJson(fileContent, new TypeToken<HashMap<String, T>>() {
        }.getType());
    }


}
