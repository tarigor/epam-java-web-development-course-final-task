package com.epam.hotel.service.impl;

import com.epam.hotel.dao.factory.DAOServiceFactory;
import com.epam.hotel.dao.factory.DAOType;
import com.epam.hotel.dao.impl.OrderDAOImpl;
import com.epam.hotel.entity.Order;
import com.epam.hotel.entity.OrderStatus;
import com.epam.hotel.service.BaseService;
import com.epam.hotel.service.BookService;

import java.sql.Date;
import java.util.ArrayList;

public class BookServiceImpl extends BaseService implements BookService {
    private OrderDAOImpl orderDAO = (OrderDAOImpl) DAOServiceFactory.getInstance().getDAO(DAOType.ORDER_DAO);

    @Override
    public void insertNewOrder(long userID,
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
                int orderIdAssigned = transaction.createConnection().performTransaction(() -> orderDAO.insertOrderDataIntoTwoTable(userID, orders.get(0)));
                System.out.println("oderIDAssigned->" + orderIdAssigned);
                for (int i = 1; i < orders.size(); i++) {
                    orderDAO.insertOrderDataIntoSingleTable(orderIdAssigned, orders.get(i));
                }
            });
        } else if (orders.size() == 1) {
            transaction.createConnection().performTransaction(() -> orderDAO.insertOrderDataIntoTwoTable(userID, orders.get(0)));
        }
    }

    private void addOrder(String[] roomsSelected, String dateFrom, String dateTo, ArrayList<Order> clientOrderRooms) {
        Date dateFromSQL = convertStringSqlDate(dateFrom);
        Date dateToSQL = convertStringSqlDate(dateTo);
        if (roomsSelected != null && roomsSelected.length != 0) {
            for (int i = 0; i < roomsSelected.length; i++) {
                clientOrderRooms.add(new Order(Integer.parseInt(roomsSelected[i]), dateFromSQL, dateToSQL, OrderStatus.WAITING_FOR_APPROVAL));
            }
        }
    }
}
