package com.epam.hotel.dao;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.dao.impl.OrderDAOImpl;
import com.epam.hotel.entity.ClientOrderRoom;
import com.epam.hotel.entity.Order;
import com.epam.hotel.types.OrderStatus;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Provides methods to be implemented in {@link OrderDAOImpl} class.
 * See description of the methods implemented:
 *
 * @see OrderDAOImpl#insertOrderDataIntoTwoTable(long, int, Order)
 * @see OrderDAOImpl#insertOrderDataIntoSingleTable(int, int, Order)
 * @see OrderDAOImpl#changeStatusOfRequest(int, OrderStatus)
 * @see OrderDAOImpl#changeStatusOfOrder(int, int, int, OrderStatus)
 * @see OrderDAOImpl#getAllOrders()
 * @see OrderDAOImpl#changeOrderStatus(String, int, int)
 */
public interface IOrderDAO {
    //first insert of new order
    int insertOrderDataIntoTwoTable(long userID, int requestId, Order order) throws DaoException;

    //when few rooms booked in a single order
    int insertOrderDataIntoSingleTable(int orderID, int requestId, Order order) throws DaoException;

    int changeStatusOfRequest(int requestID, OrderStatus orderStatus) throws DaoException;

    int changeStatusOfOrder(int orderID, int requestID, int roomID, OrderStatus orderStatus) throws DaoException;

    ArrayList<ClientOrderRoom> getAllOrders() throws DaoException;

    int changeOrderStatus(String orderStatus, int orderID, int roomID) throws DaoException, SQLException;
}
