package com.epam.hotel.service.impl;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.dao.factory.DAOServiceFactory;
import com.epam.hotel.dao.factory.DAOType;
import com.epam.hotel.dao.impl.ClientOrderDAOImpl;
import com.epam.hotel.dao.impl.UserDAOImpl;
import com.epam.hotel.entity.ClientOrderRoom;
import com.epam.hotel.entity.ClientRequest;
import com.epam.hotel.entity.User;
import com.epam.hotel.service.BaseService;
import com.epam.hotel.service.ClientService;
import com.epam.hotel.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Provides the functionality during a client activity.
 */
public class ClientServiceImpl extends BaseService implements ClientService {
    private static final Logger LOGGER = Logger.getLogger(ClientServiceImpl.class);
    private final ClientOrderDAOImpl clientOrderDAO =
            (ClientOrderDAOImpl) DAOServiceFactory.getInstance().getDaoObjectMap().get(DAOType.CLIENT_ORDER_DAO);
    private final UserDAOImpl userDAO =
            (UserDAOImpl) DAOServiceFactory.getInstance().getDaoObjectMap().get(DAOType.USER_DAO);

    public ClientServiceImpl() {
    }

    /**
     * Gets the all orders of the specific client.
     *
     * @param user an instance of {@link User} class consists a data of user for which orders will be getting.
     * @return a list of the client orders.
     */
    @Override
    public ArrayList<ClientOrderRoom> getClientOrders(User user) throws ServiceException {
        try {
            return transaction.createConnection().performTransaction(() -> clientOrderDAO.get(user));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Gets the all requests of a specific client.
     *
     * @param clientID a client ID of client for which orders will be getting.
     * @return a list of the client requests.
     */
    @Override
    public ArrayList<ClientRequest> getClientRequests(long clientID) throws ServiceException {
        try {
            return transaction.createConnection().performTransaction(() -> clientOrderDAO.getClientRequests(clientID));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Inserts a new request made by a specific client.
     *
     * @param clientID  id of the client that is requesting the insertion of the request.
     * @param persons   number of requested persons.
     * @param roomClass requested room class.
     * @param dateFrom  a date from which the room is requested.
     * @param dateTo    a date till which the room is requested.
     */
    @Override
    public void insertRequest(long clientID, int persons, String roomClass, String dateFrom, String dateTo) throws ServiceException {
        Date dateFromSQL = convertStringToSqlDate(dateFrom);
        Date dateToSQL = convertStringToSqlDate(dateTo);
        try {
            transaction.createConnection().performTransaction(() -> userDAO.insertRequest(clientID, persons, roomClass, dateFromSQL, dateToSQL));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Removes the specific request.
     *
     * @param requestID a request ID to be removed.
     */
    @Override
    public void removeRequest(int requestID) throws ServiceException {
        try {
            transaction.createConnection().performTransaction(() -> clientOrderDAO.removeRequest(requestID));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Instantiates a new object from {@link ClientRequest} class.
     *
     * @param persons   number of requested persons.
     * @param roomClass requested room class.
     * @param dateFrom  a date from which the room is requested.
     * @param dateTo    a date till which the room is requested.
     * @return an instance of {@link ClientRequest} class.
     */
    @Override
    public ClientRequest createRequest(int persons, String roomClass, String dateFrom, String dateTo) throws ServiceException {
        return new ClientRequest(persons, roomClass, convertStringToSqlDate(dateFrom), convertStringToSqlDate(dateTo));
    }

    /**
     * Changes the customer's balance when replenishing.
     *
     * @param userID       a client ID.
     * @param chargeAmount an amount of top up.
     * @return a renew value of client's balance.
     */
    @Override
    public double topUpAccount(long userID, double chargeAmount) throws ServiceException {
        try {
            return transaction.createConnection().performTransaction(() -> userDAO.modifyAccount(userID, chargeAmount));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Gets a client by requested client ID.
     *
     * @param clientID a client ID.
     * @return an instance of {@link User} consists the requested client data.
     */
    @Override
    public User getClient(long clientID) throws ServiceException {
        try {
            return transaction.createConnection().performTransaction(() -> userDAO.get(clientID));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

//    /**
//     * Removes an order from the database.
//     *
//     * @param orderID an order ID.
//     * @param roomID  a room ID belong to the order.
//     */
//    public void removeOrderFromBooking(int orderID, int roomID) {
//        transaction.createConnection().performTransaction(() -> clientOrderDAO.deleteRecord(orderID, roomID));
//    }
}
