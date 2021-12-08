package com.epam.hotel.service.impl;

import com.epam.hotel.service.PropertyFileService;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Provides the functionality to access a properties file.
 */
public class PropertiesFileServiceImpl implements PropertyFileService {
    private static final Logger LOGGER = Logger.getLogger(PropertiesFileServiceImpl.class);
    private final String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath().
            replace("/out/artifacts/epam_java_web_development_course_final_task_war_exploded/WEB-INF/classes/",
                    "/src/main/resources/");

    /**
     * Gets a specific properties file from a resources folder.
     *
     * @param propertiesPath a path to the specific properties file.
     * @return an instance of {@link Properties} class.
     */
    @Override
    public Properties getProperties(String propertiesPath) {
        String applicationPath = String.format("%s%s", rootPath, propertiesPath);
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(applicationPath));
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return properties;
    }

    public Properties getProperties(String propertiesPath, String rootPath) {
        String applicationPath = String.format("%s%s", rootPath, propertiesPath);
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(applicationPath));
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return properties;
    }
}
