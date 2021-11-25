package com.epam.hotel.dao.impl;

import com.epam.hotel.dao.BaseDao;
import com.epam.hotel.dao.OrderDAO;
import com.epam.hotel.entity.Order;

import java.sql.*;

public class OrderDAOImpl extends BaseDao implements OrderDAO {
    private static final String INSERT_ORDER_INTO_TWO_TABLES = "call insert_new_order(?,?,?,?,?,?)";
    private static final String INSERT_ORDER_INTO_SINGLE_TABLE =
            "insert into client_order_room (client_order_id, room_id, check_in_date, check_out_date, order_status) VALUES (?,?,?,?,?)";

    @Override
    public int insertOrderDataIntoTwoTable(long userID, Order order) {
        System.out.println("i'm in insert two orders");
        System.out.println("user id -> " + userID);
        System.out.println("query->" + " " + userID + " " + order.getRoomID() + " " + order.getCheckInDate() + " " + order.getCheckOutDate() + " " + order.getOrderStatus().name());
        int assignedOrderID = 0;
        try {
            CallableStatement callableStatement = connection.prepareCall(INSERT_ORDER_INTO_TWO_TABLES);
            callableStatement.setLong(1, userID);
            callableStatement.setInt(2, order.getRoomID());
            callableStatement.setDate(3, order.getCheckInDate());
            callableStatement.setDate(4, order.getCheckOutDate());
            callableStatement.setString(5, order.getOrderStatus().name());
            callableStatement.registerOutParameter(6, Types.INTEGER);
            callableStatement.executeQuery();
            ResultSet resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                assignedOrderID = resultSet.getInt(1);
            }
            System.out.println("assigned Order ID -> " + assignedOrderID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignedOrderID;
    }

    @Override
    public void insertOrderDataIntoSingleTable(int orderID, Order order) {
        System.out.println("i'm in insert one order");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_INTO_SINGLE_TABLE);
            preparedStatement.setInt(1, orderID);
            preparedStatement.setInt(2, order.getRoomID());
            preparedStatement.setDate(3, order.getCheckInDate());
            preparedStatement.setDate(4, order.getCheckOutDate());
            preparedStatement.setString(5, order.getOrderStatus().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
