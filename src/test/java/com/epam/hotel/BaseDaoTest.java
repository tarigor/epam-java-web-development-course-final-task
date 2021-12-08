package com.epam.hotel;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import com.epam.hotel.connectionmanager.connectionpool.impl.ConnectionPoolImpl;
import com.epam.hotel.connectionmanager.transaction.impl.TransactionImpl;
import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.service.impl.DatabaseConnectionServiceImpl;
import com.epam.hotel.service.impl.ReusablePoolServiceImpl;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class BaseDaoTest {
    public static final String TEST_DATABASE_PROPERTIES = "src/test/resources/testDatabase.properties";
    public static final String TEST_DB_NAME = "testHotelDB";
    public static final String TEST_DB_SQL = "testdb.sql";
    private static final Logger LOGGER = Logger.getLogger(BaseDaoTest.class);
    private static DB db;

    protected static void init() throws ManagedProcessException, DaoException {
        initDB();
        initConnection();
    }

    protected static void stopDB() throws ManagedProcessException {
        db.stop();
    }

    private static void initConnection() throws DaoException {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(TEST_DATABASE_PROPERTIES));
        } catch (IOException e) {
            LOGGER.error(e);
        }
        DatabaseConnectionServiceImpl databaseConnectionService = new DatabaseConnectionServiceImpl();
        databaseConnectionService.init(properties);
        ReusablePoolServiceImpl reusablePoolService = new ReusablePoolServiceImpl();
        ConnectionPoolImpl.getInstance().initForTest(databaseConnectionService, reusablePoolService, properties);
        TransactionImpl.getInstance().createConnection();
    }

    private static void initDB() throws ManagedProcessException {
        DBConfigurationBuilder config = DBConfigurationBuilder.newBuilder();
        config.setPort(3303);
        db = DB.newEmbeddedDB(config.build());
        db.start();
        String dbName = TEST_DB_NAME;
        db.createDB(dbName);
        db.source(TEST_DB_SQL);
    }

}
