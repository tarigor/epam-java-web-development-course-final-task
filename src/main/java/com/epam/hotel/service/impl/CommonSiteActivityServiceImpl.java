package com.epam.hotel.service.impl;

import com.epam.hotel.connectionmanager.transaction.impl.TransactionImpl;
import com.epam.hotel.dao.factory.DAOServiceFactory;
import com.epam.hotel.dao.factory.DAOType;
import com.epam.hotel.dao.impl.UserDAOImpl;
import com.epam.hotel.entity.User;
import com.epam.hotel.service.CommonSiteActivityService;

/**
 * The class provides the service methods of the common site activities.
 */
public class CommonSiteActivityServiceImpl implements CommonSiteActivityService {
    private final TransactionImpl transaction = TransactionImpl.getInstance();
    private UserDAOImpl userDAO = (UserDAOImpl) DAOServiceFactory.getInstance().getDAO(DAOType.USER_DAO);

    public CommonSiteActivityServiceImpl() {

    }

    @Override
    public User checkUserForExistingAndRightPasswordInputted(User user) {
        return null;
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
