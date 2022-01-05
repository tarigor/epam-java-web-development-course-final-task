package com.epam.hotel.utility;

import com.epam.hotel.entity.User;
import com.epam.hotel.service.exception.ServiceException;
import com.epam.hotel.types.UserType;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class PasswordHandlerTest {
    private static final Logger LOGGER = Logger.getLogger(PasswordHandlerTest.class);

    @Test
    void testPasswordEncryptDecrypt() throws ServiceException {
        String initPass = "Qwer1234!";

        User user = new User(0, "Dyadya", "Stepa", UserType.CLIENT, "pupkin@yopmail.com", initPass);
        LOGGER.info(String.format("hash -> %d", user.hashCode()));

        PasswordHandler passwordHandler = new PasswordHandler().setEncryptionKey(user.hashCode());
        String encrPass = passwordHandler.encryptPassword(initPass);
        LOGGER.info(String.format("encrPass -> %s", encrPass));

        String decrPass = passwordHandler.decryptPassword(encrPass);
        LOGGER.info(String.format("decrPass -> %s", decrPass));

        Assert.assertTrue(initPass.contentEquals(decrPass));
    }
}