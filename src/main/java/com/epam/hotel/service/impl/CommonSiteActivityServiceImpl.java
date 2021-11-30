package com.epam.hotel.service.impl;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.dao.factory.DAOServiceFactory;
import com.epam.hotel.dao.factory.DAOType;
import com.epam.hotel.dao.impl.UserDAOImpl;
import com.epam.hotel.entity.User;
import com.epam.hotel.entity.UserLoginError;
import com.epam.hotel.service.BaseService;
import com.epam.hotel.service.CommonSiteActivityService;
import com.epam.hotel.service.exception.ServiceException;

/**
 * Provides the functionality of common site activities.
 */
public class CommonSiteActivityServiceImpl extends BaseService implements CommonSiteActivityService {
    private UserDAOImpl userDAO = (UserDAOImpl) DAOServiceFactory.getInstance().getDaoObjectMap().get(DAOType.USER_DAO);

    public CommonSiteActivityServiceImpl() {

    }

    /**
     * Checks if the requested client is exist in the database.
     *
     * @param user an instance of {@link User} which is consists a logged client data.
     * @return an instance of {@link User} of thw client data stored in a database.
     */
    @Override
    public User checkUserForExistingAndRightPasswordInputted(User user) throws ServiceException {
        User userFromDB;
        try {
            userFromDB = transaction.createConnection().performTransaction(() -> userDAO.get(user.hashCode()));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        if (userFromDB != null) {
            if (user.getEmail().equals(userFromDB.getEmail()) &&
                    user.getPassword().equals(userFromDB.getPassword())) {
                System.out.println("user are correct");
                return userFromDB;
            } else {
                System.out.println("the password is not correct");
                UserLoginError.setType("login.wrong.password");
                return null;
            }
        } else {
            System.out.println("there is no such a user");
            UserLoginError.setType("login.no.such.user");
            return null;
        }
    }

    /**
     * Does a new user registration.
     *
     * @param user an instance of {@link User} which is consist a data of a new user.
     * @return a boolean result of user adding.
     */
    @Override
    public boolean doNewUserRegistration(User user) throws ServiceException {
        try {
            long userID = transaction.createConnection().performTransaction(() -> userDAO.checkIfUserExist(user.hashCode()));
            if (userID != user.hashCode()) {
                transaction.createConnection().performTransaction(() -> userDAO.insert(user));
                return true;
            }
            return false;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
