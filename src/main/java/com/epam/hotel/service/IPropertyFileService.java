package com.epam.hotel.service;

import java.util.Properties;

/**
 * Provides methods to be implemented in {@link com.epam.hotel.service.impl.PropertiesFileServiceImpl} class.
 * See description of the methods implemented:
 *
 * @see com.epam.hotel.service.impl.PropertiesFileServiceImpl#getProperties(String)
 */
public interface IPropertyFileService {
    Properties getProperties(String propertiesPath);
}
