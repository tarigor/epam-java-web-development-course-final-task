package com.epam.hotel.service.impl;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.dao.factory.DAOServiceFactory;
import com.epam.hotel.dao.factory.DAOType;
import com.epam.hotel.dao.impl.ClientOrderDAOImpl;
import com.epam.hotel.dao.impl.RoomDAOImpl;
import com.epam.hotel.dao.impl.UserDAOImpl;
import com.epam.hotel.entity.ClientOrderRoom;
import com.epam.hotel.entity.ClientRequest;
import com.epam.hotel.entity.User;
import com.epam.hotel.service.BaseService;
import com.epam.hotel.service.ClientService;

import java.sql.Date;
import java.util.ArrayList;

public class ClientServiceImpl extends BaseService implements ClientService {
    private final ClientOrderDAOImpl clientOrderDAO =
            (ClientOrderDAOImpl) DAOServiceFactory.getInstance().getDAO(DAOType.CLIENT_ORDER_DAO);
    private final UserDAOImpl userDAO =
            (UserDAOImpl) DAOServiceFactory.getInstance().getDAO(DAOType.USER_DAO);
    private final RoomDAOImpl roomDAO =
            (RoomDAOImpl) DAOServiceFactory.getInstance().getDAO(DAOType.ROOM_DAO);

    public ClientServiceImpl() {
    }

    @Override
    public ArrayList<ClientOrderRoom> getClientOrders(User user) {
        ArrayList<ClientOrderRoom> clientOrders = transaction.createConnection().performTransaction(() -> clientOrderDAO.get(user));
        clientOrders.forEach(System.out::println);
        for (ClientOrderRoom order : clientOrders) {
            System.out.println("client order>" + order.toString());
        }
        return clientOrders;
    }

    @Override
    public ArrayList<ClientRequest> getClientRequests(long clientID) {
        ArrayList<ClientRequest> clientRequests = transaction.createConnection().performTransaction(() -> clientOrderDAO.getClientRequests(clientID));
        for (ClientRequest request : clientRequests) {
            System.out.println("client request->" + request.toString());
        }
        return clientRequests;
    }

    @Override
    public void insertRequest(long clientID, int persons, String roomClass, String dateFrom, String dateTo) {
        Date dateFromSQL = convertStringToSqlDate(dateFrom);
        Date dateToSQL = convertStringToSqlDate(dateTo);
        transaction.createConnection().performTransaction(() -> userDAO.insertRequest(clientID, persons, roomClass, dateFromSQL, dateToSQL));
    }

    @Override
    public void removeRequest(int requestID) {
        transaction.createConnection().performTransaction(() -> clientOrderDAO.removeRequest(requestID));
    }

    @Override
    public ClientRequest createRequest(int persons, String roomClass, String dateFrom, String dateTo) {
        return new ClientRequest(persons, roomClass, convertStringToSqlDate(dateFrom), convertStringToSqlDate(dateTo));
    }

    @Override
    public double chargeAccount(long userID, double chargeAmount) {
        return transaction.createConnection().performTransaction(() -> userDAO.modifyAccount(userID, chargeAmount));
    }

    @Override
    public User getClient(long clientID) {
        return transaction.createConnection().performTransaction(() -> userDAO.get(clientID));
    }

    public void removeOrderFromBooking(int orderID, int roomID) {
        transaction.createConnection().performTransaction(() -> {
            try {
                clientOrderDAO.deleteRecord(orderID, roomID);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        });
    }
}
