package com.epam.hotel.service.impl;

import org.junit.jupiter.api.Test;

import java.util.Properties;

class PropertiesFileServiceImplTest {

    @Test
    void getProperties() {
        PropertiesFileServiceImpl propertiesFileService = new PropertiesFileServiceImpl();
        Properties properties = propertiesFileService.getProperties("database.properties");
        System.out.println(properties.getProperty("connection.expiration.time"));
    }
}