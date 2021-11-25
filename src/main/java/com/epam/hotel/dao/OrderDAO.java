package com.epam.hotel.dao;

import com.epam.hotel.entity.Order;

public interface OrderDAO {
    //first insert of new order
    int insertOrderDataIntoTwoTable(long userID, Order order);

    //when few rooms booked in a single order
    void insertOrderDataIntoSingleTable(int orderID, Order order);
}
