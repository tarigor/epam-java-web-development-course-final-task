package com.epam.hotel.service;

import java.util.Properties;

/**
 * The interface provides the methods to be implemented by of {@link com.epam.hotel.service.impl.PropertiesFileServiceImpl} class.
 */
public interface PropertyFileService {
    Properties getProperties(String propertiesPath);
}
