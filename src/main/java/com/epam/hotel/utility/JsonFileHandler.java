package com.epam.hotel.utility;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class JsonFileHandler<T> {
    private final Gson gson;
    private String jsonFilePath;

    public JsonFileHandler(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
        gson = new Gson();
    }

    /**
     * Methods provides json file parsing into the HashMap.
     *
     * @return HashMap contains key/value of command name/http command instance)
     */
    public HashMap<String, T> getMapOfCommandFromJson() {
//        System.out.println("rootPath->" + Thread.currentThread().getContextClassLoader().
//                getResource("").getPath());
        // for tomcat run
        String rootPath = Thread.currentThread().getContextClassLoader().
                getResource("").getPath().
                replace("/out/artifacts/epam_java_web_development_course_final_task_war_exploded/WEB-INF/classes/", "/src/main/resources/factory/");
        //for test
//        String rootPath = Thread.currentThread().getContextClassLoader().
//                getResource("").getPath().
//                replace("/target/test-classes/", "/src/main/resources/factory/");
//        System.out.println("rootPath + jsonFilePath->" + rootPath + jsonFilePath);
        String fileContent = null;
        try {
            fileContent = new String(Files.readAllBytes(Paths.get(rootPath + jsonFilePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gson.fromJson(fileContent, new TypeToken<HashMap<String, T>>() {
        }.getType());
    }


}
