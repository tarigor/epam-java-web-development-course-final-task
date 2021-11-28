package com.epam.hotel.dao;

import com.epam.hotel.entity.Order;
import com.epam.hotel.entity.OrderStatus;

public interface OrderDAO {
    //first insert of new order
    int insertOrderDataIntoTwoTable(long userID, int requestId, Order order);

    //when few rooms booked in a single order
    void insertOrderDataIntoSingleTable(int orderID, int requestId, Order order);

    void changeStatusOfRequest(int requestID, OrderStatus orderStatus);

    void changeStatusOfOrder(int orderID, int requestID,int roomID, OrderStatus orderStatus);
}
