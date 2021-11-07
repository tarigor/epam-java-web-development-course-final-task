package com.epam.hotel.service.impl;

import com.epam.hotel.service.PropertyFileService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileServiceImpl implements PropertyFileService {
    private final String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath().replace("/out/artifacts/epam_java_web_development_course_final_task_war_exploded/WEB-INF/classes/","/src/main/resources/");

    @Override
    public Properties getProperties(String propertiesPath) {
        String applicationPath = String.format("%s%s", rootPath, propertiesPath);
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(applicationPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
