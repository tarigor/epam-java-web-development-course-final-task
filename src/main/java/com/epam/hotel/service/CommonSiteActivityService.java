package com.epam.hotel.service;

import com.epam.hotel.entity.User;
import com.epam.hotel.service.exception.ServiceException;

/**
 * Provides methods to be implemented in {@link com.epam.hotel.service.impl.CommonSiteActivityServiceImpl} class.
 * See description of the methods implemented:
 *
 * @see com.epam.hotel.service.impl.CommonSiteActivityServiceImpl#checkUserForExistingAndRightPasswordInputted(User)
 * @see com.epam.hotel.service.impl.CommonSiteActivityServiceImpl#doNewUserRegistration(User)
 */
public interface CommonSiteActivityService {
    User checkUserForExistingAndRightPasswordInputted(User user) throws ServiceException;

    boolean doNewUserRegistration(User user) throws ServiceException;

}
