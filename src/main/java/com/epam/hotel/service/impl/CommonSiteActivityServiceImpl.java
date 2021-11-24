package com.epam.hotel.service.impl;

import com.epam.hotel.dao.factory.DAOServiceFactory;
import com.epam.hotel.dao.factory.DAOType;
import com.epam.hotel.dao.impl.UserDAOImpl;
import com.epam.hotel.entity.User;
import com.epam.hotel.entity.UserLoginError;
import com.epam.hotel.service.BaseService;
import com.epam.hotel.service.CommonSiteActivityService;

/**
 * The class provides the service methods of the common site activities.
 */
public class CommonSiteActivityServiceImpl extends BaseService implements CommonSiteActivityService {
    private UserDAOImpl userDAO = (UserDAOImpl) DAOServiceFactory.getInstance().getDAO(DAOType.USER_DAO);

    public CommonSiteActivityServiceImpl() {

    }

    @Override
    public User checkUserForExistingAndRightPasswordInputted(User user) {
        System.out.println("userID trying to log -> " + user.hashCode());
        User userFromDB = transaction.createConnection().performTransaction(() -> userDAO.get(user.hashCode()));

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

    @Override
    public void doUserLogout(User user) {

    }

    @Override
    public boolean doNewUserRegistration(User user) {
        long userID = transaction.createConnection().performTransaction(() -> userDAO.checkIfUserExist(user.hashCode()));
        if (userID != user.hashCode()) {
            transaction.createConnection().performTransaction(() -> userDAO.insert(user));
            return true;
        }
        return false;
    }
}
