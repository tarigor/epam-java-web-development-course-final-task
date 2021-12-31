package com.epam.hotel.dao.impl;

import ch.vorburger.exec.ManagedProcessException;
import com.epam.hotel.BaseDaoTest;
import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.entity.User;
import com.epam.hotel.types.UserType;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class IUserDAOImplDaoTest extends BaseDaoTest {
    private static final Logger LOGGER = Logger.getLogger(IUserDAOImplDaoTest.class);
    private final UserDAOImpl userDAO = new UserDAOImpl();

    @BeforeAll
    static void setUp() throws DaoException, ManagedProcessException {
        init();
    }

    @AfterAll
    static void tearDown() throws ManagedProcessException {
        stopDB();
    }

    @Test
    void testCheckIfUserExist() throws DaoException {
        User testUser = new User("test", "test", UserType.CLIENT, "test1@test.com", "test");
        long testUserID = testUser.hashCode();
        userDAO.insert(testUser);
        LOGGER.info(String.format("ID of inserted testUser-> %d", testUserID));

        Assert.assertEquals(testUserID, userDAO.checkIfUserExist(testUserID));
    }

    @Test
    void testInsert() throws DaoException {
        User testUser = new User("test", "test", UserType.CLIENT, "test2@test.com", "test");
        int count = userDAO.insert(testUser);

        LOGGER.info(String.format("count -> %d", count));
        Assert.assertEquals(1, count);
    }

    @Test
    void testGet() throws DaoException {
        User testUser = new User("test", "test", UserType.CLIENT, "test3@test.com", "test");
        long testUserID = testUser.hashCode();
        LOGGER.info(String.format("ID of inserted testUser-> %d", testUserID));
        userDAO.insert(testUser);

        User testUserFromDB = userDAO.get(testUserID);
        LOGGER.info(String.format("ID of testUserFromDB -> %d", testUserFromDB.hashCode()));
        Assert.assertEquals(testUserID, testUserFromDB.hashCode());
    }

    @Test
    void testTopUpAccount() throws DaoException {
        User testUser = new User("test", "test", UserType.CLIENT, "test4@test.com", "test");
        long testUserID = testUser.hashCode();
        LOGGER.info(String.format("ID of inserted testUser-> %d", testUserID));
        userDAO.insert(testUser);
        User testUserFromDB = userDAO.get(testUserID);
        double balanceOftestUserFromDBBeforeTopUp = testUserFromDB.getAccount();
        LOGGER.info(String.format("balance of testUser fromDB before top up-> %f", balanceOftestUserFromDBBeforeTopUp));

        double replenishmentValue = 10.0;
        userDAO.topUpAccount(testUserID, replenishmentValue);
        User testUserFromDBAfterTopUp = userDAO.get(testUserID);
        double balanceOftestUserFromDBAfterTopUp = testUserFromDBAfterTopUp.getAccount();
        LOGGER.info(String.format("balance of testUser fromDB after top up-> %f", balanceOftestUserFromDBAfterTopUp));

        double expectedBalance = balanceOftestUserFromDBBeforeTopUp + replenishmentValue;
        LOGGER.info(String.format("expected balance -> %f", expectedBalance));
        Assert.assertEquals(expectedBalance, balanceOftestUserFromDBAfterTopUp, 0.0);
    }

}