package com.epam.hotel.service;

import com.epam.hotel.entity.User;

public interface CommonSiteActivityService {
    User checkUserForExistingAndRightPasswordInputted(User user);
    void doUserLogout(User user);
    void doNewUserRegistration(User user);
}
