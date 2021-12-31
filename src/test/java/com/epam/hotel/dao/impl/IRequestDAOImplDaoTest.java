package com.epam.hotel.dao.impl;

import ch.vorburger.exec.ManagedProcessException;
import com.epam.hotel.BaseDaoTest;
import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.entity.ClientRequest;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;

class IRequestDAOImplDaoTest extends BaseDaoTest {
    private static final Logger LOGGER = Logger.getLogger(RequestDAOImpl.class);
    private final RequestDAOImpl requestDAO = new RequestDAOImpl();

    @BeforeAll
    static void setUp() throws DaoException, ManagedProcessException {
        init();
    }

    @AfterAll
    static void tearDown() throws ManagedProcessException {
        stopDB();
    }

    @Test
    void getAllRequests() {
    }

    @Test
    void getRequestByIDAndEmail() {
    }

    @Test
    void insertRequest() throws DaoException {
        int insertedTestRequestID = requestDAO.insertRequest(88574861, 1, "test", new Date(1L), new Date(2L));
        LOGGER.info(String.format("ID of insertedTestRequest-> %d", insertedTestRequestID));
        Assert.assertEquals(1, insertedTestRequestID);

        ClientRequest clientRequest = requestDAO.getRequestByIDAndEmail(insertedTestRequestID, "pupkina@mail.com");
        LOGGER.info(String.format("ID of request from DB-> %d", clientRequest.getRequestID()));
        Assert.assertEquals(insertedTestRequestID, clientRequest.getRequestID());
    }
}