package com.epam.hotel.service;

import com.epam.hotel.entity.User;

/**
 * The interface provides the methods to be implemented by of {@link com.epam.hotel.service.impl.DatabaseConnectionServiceImpl} class.
 */
public interface CommonSiteActivityService {
    User checkUserForExistingAndRightPasswordInputted(User user);

    void doUserLogout(User user);

    boolean doNewUserRegistration(User user);

}
