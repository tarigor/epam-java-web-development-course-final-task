package com.epam.hotel.service.impl;

import com.epam.hotel.dao.factory.DAOServiceFactory;
import com.epam.hotel.dao.factory.DAOType;
import com.epam.hotel.dao.impl.UserDAOImpl;
import com.epam.hotel.entity.User;
import com.epam.hotel.service.CommonSiteActivityService;

/**
 * The class provides the service methods of the common site activities.
 */
public class CommonSiteActivityServiceImpl implements CommonSiteActivityService {
    private UserDAOImpl userDAO;

    public CommonSiteActivityServiceImpl() {
        userDAO = (UserDAOImpl) DAOServiceFactory.getInstance().getDAO(DAOType.USER_DAO);
    }

    @Override
    public User checkUserForExistingAndRightPasswordInputted(String userFirstName, String userFamilyName, String password) {

        return null;
    }

    @Override
    public void doUserLogout(User user) {

    }

    @Override
    public void doNewUserRegistration(User user) {

    }
}
