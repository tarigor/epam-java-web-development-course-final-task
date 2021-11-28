package com.epam.hotel.dao.impl;

import com.epam.hotel.dao.BaseDao;
import com.epam.hotel.dao.OrderDAO;
import com.epam.hotel.entity.Order;
import com.epam.hotel.entity.OrderStatus;

import java.sql.*;

public class OrderDAOImpl extends BaseDao implements OrderDAO {
    private static final String INSERT_ORDER_INTO_TWO_TABLES = "call insert_new_order(?,?,?,?,?,?,?)";
    private static final String INSERT_ORDER_INTO_SINGLE_TABLE =
            "insert into `order` (client_order_id, request_id, room_id, check_in_date, check_out_date, order_status) VALUES (?,?,?,?,?,?)";
    private static final String CHANGE_REQUEST_STATUS = "UPDATE hotelDB.request t SET t.request_status = ? WHERE t.request_id = ?";
    private static final String CHANGE_ORDER_STATUS = "UPDATE hotelDB.`order` t SET t.order_status = ? WHERE t.client_order_id = ? AND t.request_id = ? AND t.room_id =?";

    @Override
    public int insertOrderDataIntoTwoTable(long userID, int requestID, Order order) {
        System.out.println("i'm in insert two orders");
        System.out.println("user id -> " + userID);
        System.out.println("query->" + " " + userID + " " + order.getRoomID() + " " + order.getCheckInDate() + " " + order.getCheckOutDate() + " " + order.getOrderStatus().name());
        int assignedOrderID = 0;
        try {
            CallableStatement callableStatement = connection.prepareCall(INSERT_ORDER_INTO_TWO_TABLES);
            callableStatement.setLong(1, userID);
            callableStatement.setInt(2, requestID);
            callableStatement.setInt(3, order.getRoomID());
            callableStatement.setDate(4, order.getCheckInDate());
            callableStatement.setDate(5, order.getCheckOutDate());
            callableStatement.setString(6, order.getOrderStatus().name());
            callableStatement.registerOutParameter(7, Types.INTEGER);
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
    public void insertOrderDataIntoSingleTable(int orderID, int requestID, Order order) {
        System.out.println("i'm in insert one order");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_INTO_SINGLE_TABLE);
            preparedStatement.setInt(1, orderID);
            preparedStatement.setInt(2, requestID);
            preparedStatement.setInt(3, order.getRoomID());
            preparedStatement.setDate(4, order.getCheckInDate());
            preparedStatement.setDate(5, order.getCheckOutDate());
            preparedStatement.setString(6, order.getOrderStatus().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeStatusOfRequest(int requestID, OrderStatus orderStatus) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_REQUEST_STATUS);
            preparedStatement.setString(1, orderStatus.name());
            preparedStatement.setInt(2, requestID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeStatusOfOrder(int orderID, int requestID, int roomID, OrderStatus orderStatus) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_ORDER_STATUS);
            preparedStatement.setString(1, orderStatus.name());
            preparedStatement.setInt(2, orderID);
            preparedStatement.setInt(3, requestID);
            preparedStatement.setInt(4, roomID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
