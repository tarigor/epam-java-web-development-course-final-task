package com.epam.hotel.service.impl;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.dao.factory.DAOServiceFactory;
import com.epam.hotel.dao.factory.DAOType;
import com.epam.hotel.dao.impl.OrderDAOImpl;
import com.epam.hotel.dao.impl.UserDAOImpl;
import com.epam.hotel.entity.Order;
import com.epam.hotel.service.BaseService;
import com.epam.hotel.service.BookService;
import com.epam.hotel.service.exception.ServiceException;
import com.epam.hotel.types.OrderStatus;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Provides the functionality during booking of the rooms by a client.
 */
public class BookServiceImpl extends BaseService implements BookService {
    private static final Logger LOGGER = Logger.getLogger(BookServiceImpl.class);
    private final OrderDAOImpl orderDAO = (OrderDAOImpl) DAOServiceFactory.getInstance().getDaoObjectMap().get(DAOType.ORDER_DAO);
    private final UserDAOImpl userDAO = (UserDAOImpl) DAOServiceFactory.getInstance().getDaoObjectMap().get(DAOType.USER_DAO);

    /**
     * Inserts a new order made by the client.
     *
     * @param userID              an user ID.
     * @param requestID           a request ID which will be belong to the new order.
     * @param singleRoomsSelected an array of the single class rooms.
     * @param doubleRoomsSelected an array of the double class rooms.
     * @param suiteRoomsSelected  an array of the suite class rooms.
     * @param deluxeRoomsSelected an array of the deluxe class rooms.
     * @param dateFrom            a date from which a room booking is requested.
     * @param dateTo              a date till which a room booking is requested.
     */
    @Override
    public void insertNewOrder(long userID,
                               int requestID,
                               String[] singleRoomsSelected,
                               String[] doubleRoomsSelected,
                               String[] suiteRoomsSelected,
                               String[] deluxeRoomsSelected,
                               String dateFrom,
                               String dateTo) throws ServiceException {

        ArrayList<Order> orders = new ArrayList<>();
        addOrder(singleRoomsSelected, dateFrom, dateTo, orders);
        addOrder(doubleRoomsSelected, dateFrom, dateTo, orders);
        addOrder(suiteRoomsSelected, dateFrom, dateTo, orders);
        addOrder(deluxeRoomsSelected, dateFrom, dateTo, orders);
        try {
            if (orders.size() > 1) {
                executor.createConnection().executeAsTransaction(() -> {
                    int orderIdAssigned = orderDAO.insertOrderDataIntoTwoTable(userID, requestID, orders.get(0));
                    for (int i = 1; i < orders.size(); i++) {
                        orderDAO.insertOrderDataIntoSingleTable(orderIdAssigned, requestID, orders.get(i));
                    }
                    orderDAO.changeStatusOfRequest(requestID, OrderStatus.REQUEST_PROCESSED);
                    return orderIdAssigned;
                });
            } else if (orders.size() == 1) {
                executor.createConnection().executeAsTransaction(() -> {
                    int count = orderDAO.insertOrderDataIntoTwoTable(userID, requestID, orders.get(0));
                    orderDAO.changeStatusOfRequest(requestID, OrderStatus.REQUEST_PROCESSED);
                    return count;
                });
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Changes the customer's balance when the customer pays for a new order.
     *
     * @param userID    an user ID hwo is payment doing.
     * @param orderID   an order ID to nbe paid.
     * @param requestID a request ID belongs to order.
     * @param roomID    a room ID belong to order.
     * @param roomPrice a room price.
     */
    @Override
    public void payInvoice(long userID, int orderID, int requestID, int roomID, double roomPrice) throws ServiceException {
        try {
            double changeValue = roomPrice * (-1);
            executor.createConnection().executeAsTransaction(() -> {
                double balance = userDAO.topUpAccount(userID, changeValue);
                orderDAO.changeStatusOfOrder(orderID, requestID, roomID, OrderStatus.PAID_AND_BOOKED);
                orderDAO.changeStatusOfRequest(requestID, OrderStatus.REQUEST_COMPLETED);
                return balance;
            });
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private void addOrder(String[] roomsSelected, String dateFrom, String dateTo, ArrayList<Order> clientOrderRooms) throws ServiceException {
        Date dateFromSQL = convertStringToSqlDate(dateFrom);
        Date dateToSQL = convertStringToSqlDate(dateTo);
        if (roomsSelected != null && roomsSelected.length != 0) {
            for (String s : roomsSelected) {
                clientOrderRooms.add(new Order(Integer.parseInt(s), dateFromSQL, dateToSQL, OrderStatus.APPROVED_WAITING_FOR_PAYMENT));
            }
        }
    }
}
