package com.epam.hotel.service.impl;

import com.epam.hotel.dao.factory.DAOServiceFactory;
import com.epam.hotel.dao.factory.DAOType;
import com.epam.hotel.dao.impl.OrderDAOImpl;
import com.epam.hotel.dao.impl.UserDAOImpl;
import com.epam.hotel.entity.Order;
import com.epam.hotel.entity.OrderStatus;
import com.epam.hotel.service.BaseService;
import com.epam.hotel.service.BookService;

import java.sql.Date;
import java.util.ArrayList;

public class BookServiceImpl extends BaseService implements BookService {
    private OrderDAOImpl orderDAO = (OrderDAOImpl) DAOServiceFactory.getInstance().getDAO(DAOType.ORDER_DAO);
    private UserDAOImpl userDAO = (UserDAOImpl) DAOServiceFactory.getInstance().getDAO(DAOType.USER_DAO);

    @Override
    public void insertNewOrder(long userID,
                               int requestID,
                               String[] singleRoomsSelected,
                               String[] doubleRoomsSelected,
                               String[] suiteRoomsSelected,
                               String[] deluxeRoomsSelected,
                               String dateFrom,
                               String dateTo) {

        ArrayList<Order> orders = new ArrayList<>();
        addOrder(singleRoomsSelected, dateFrom, dateTo, orders);
        addOrder(doubleRoomsSelected, dateFrom, dateTo, orders);
        addOrder(suiteRoomsSelected, dateFrom, dateTo, orders);
        addOrder(deluxeRoomsSelected, dateFrom, dateTo, orders);

        System.out.println("orders size -> " + orders.size());

        if (orders.size() > 1) {
            transaction.createConnection().performTransaction(() -> {
                int orderIdAssigned = orderDAO.insertOrderDataIntoTwoTable(userID, requestID, orders.get(0));
                System.out.println("oderIDAssigned->" + orderIdAssigned);
                for (int i = 1; i < orders.size(); i++) {
                    orderDAO.insertOrderDataIntoSingleTable(orderIdAssigned, requestID, orders.get(i));
                }
                orderDAO.changeStatusOfRequest(requestID, OrderStatus.REQUEST_PROCESSED);
            });
        } else if (orders.size() == 1) {
            transaction.createConnection().performTransaction(() -> {
                orderDAO.insertOrderDataIntoTwoTable(userID, requestID, orders.get(0));
                orderDAO.changeStatusOfRequest(requestID, OrderStatus.REQUEST_PROCESSED);
            });
        }
    }

    @Override
    public void payInvoice(long userID, int orderID, int requestID, int roomID, double roomPrice) {
        double changeValue = roomPrice * (-1);
        transaction.createConnection().performTransaction(() -> {
            userDAO.modifyAccount(userID, changeValue);
            orderDAO.changeStatusOfOrder(orderID, requestID, roomID, OrderStatus.PAID_AND_BOOKED);
        });
    }

    private void addOrder(String[] roomsSelected, String dateFrom, String dateTo, ArrayList<Order> clientOrderRooms) {
        Date dateFromSQL = convertStringToSqlDate(dateFrom);
        Date dateToSQL = convertStringToSqlDate(dateTo);
        if (roomsSelected != null && roomsSelected.length != 0) {
            for (int i = 0; i < roomsSelected.length; i++) {
                clientOrderRooms.add(new Order(Integer.parseInt(roomsSelected[i]), dateFromSQL, dateToSQL, OrderStatus.APPROVED_WAITING_FOR_PAYMENT));
            }
        }
    }
}
