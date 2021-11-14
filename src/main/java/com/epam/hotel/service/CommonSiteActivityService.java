package com.epam.hotel.service;

import com.epam.hotel.entity.User;

/**
 * The interface provides the methods to be implemented by of {@link com.epam.hotel.service.impl.DatabaseConnectionServiceImpl} class.
 */
public interface CommonSiteActivityService {
    User checkUserForExistingAndRightPasswordInputted(String userFirstName, String userFamilyName, String password);

    void doUserLogout(User user);

    void doNewUserRegistration(User user);
}
