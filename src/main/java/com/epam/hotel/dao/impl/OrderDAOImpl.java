package com.epam.hotel.dao.impl;

import com.epam.hotel.dao.BaseDao;
import com.epam.hotel.dao.OrderDAO;
import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.entity.ClientOrderRoom;
import com.epam.hotel.entity.Order;
import com.epam.hotel.types.OrderStatus;

import java.sql.*;
import java.util.ArrayList;

/**
 * Provides the functionality when working with a "order" table of the database.
 */
public class OrderDAOImpl extends BaseDao implements OrderDAO {
    private static final String INSERT_ORDER_INTO_TWO_TABLES = "call insert_new_order(?,?,?,?,?,?,?)";
    private static final String INSERT_ORDER_INTO_SINGLE_TABLE =
            "insert into `order` (client_order_id, request_id, room_id, check_in_date, check_out_date, order_status) VALUES (?,?,?,?,?,?)";
    private static final String CHANGE_REQUEST_STATUS = "UPDATE hotelDB.request t SET t.request_status = ? WHERE t.request_id = ?";
    private static final String CHANGE_ORDER_STATUS = "UPDATE hotelDB.`order` t SET t.order_status = ? WHERE t.client_order_id = ? AND t.request_id = ? AND t.room_id =?";
    private static final String GET_ALL_ORDERS_OF_ALL_CLIENTS = "" +
            "SELECT client_order_id,\n" +
            "       request_id,\n" +
            "       first_name,\n" +
            "       last_name,\n" +
            "       email,\n" +
            "       room_id,\n" +
            "       room_class,\n" +
            "       check_in_date,\n" +
            "       check_out_date,\n" +
            "       order_status\n" +
            "FROM `order`\n" +
            "         join client_order c on `order`.client_order_id = c.client_order_room_id\n" +
            "         join user u on c.client_id = u.id\n" +
            "         join client_order co on `order`.client_order_id = co.client_order_room_id\n" +
            "         join room r on `order`.room_id = r.id\n" +
            "         join room_class rc on r.room_class_id = rc.id";
    private static final String CHANGE_ORDER_STATUS_V2 = "UPDATE hotelDB.`order` t SET t.order_status = ? WHERE t.client_order_id = ? AND t.room_id = ?";

    /**
     * Provides a inserting the records into a two tables "client_order" and "order" tables using stored procedure.
     *
     * @param userID    an user ID assigned for inserted order.
     * @param requestID a requestID assigned for inserted order.
     * @param order     an instance of {@link Order} contains order data.
     * @return an assigned order ID of the inserted order.
     * @throws DaoException in case of error occurs while accessing to database.
     */
    @Override
    public int insertOrderDataIntoTwoTable(long userID, int requestID, Order order) throws DaoException {
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
            return assignedOrderID;
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    /**
     * Provides an inserting of the new order in the "order" table.
     *
     * @param orderID   an order ID of the order to be inserted.
     * @param requestID a request ID belongs to the order ID>
     * @param order     an instance of {@link Order} contains order data.
     * @throws DaoException in case of error occurs while accessing to database.
     */
    @Override
    public int insertOrderDataIntoSingleTable(int orderID, int requestID, Order order) throws DaoException {
        int count;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_INTO_SINGLE_TABLE);
            preparedStatement.setInt(1, orderID);
            preparedStatement.setInt(2, requestID);
            preparedStatement.setInt(3, order.getRoomID());
            preparedStatement.setDate(4, order.getCheckInDate());
            preparedStatement.setDate(5, order.getCheckOutDate());
            preparedStatement.setString(6, order.getOrderStatus().name());
            count = preparedStatement.executeUpdate();
            return count;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Provides a changing of the status of the specific request.
     *
     * @param requestID   a request ID of the request for which status will be changed.
     * @param orderStatus an order status on which will be changed.
     * @throws DaoException in case of error occurs while accessing to database.
     */
    @Override
    public int changeStatusOfRequest(int requestID, OrderStatus orderStatus) throws DaoException {
        int count;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_REQUEST_STATUS);
            preparedStatement.setString(1, orderStatus.name());
            preparedStatement.setInt(2, requestID);
            count = preparedStatement.executeUpdate();
            return count;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Provides a changing of the status of the specific request.
     *
     * @param requestID   a request ID of the order for which status will be changed.
     * @param orderStatus an order status on which will be changed.
     * @throws DaoException in case of error occurs while accessing to database.
     */
    @Override
    public int changeStatusOfOrder(int orderID, int requestID, int roomID, OrderStatus orderStatus) throws DaoException {
        int count;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_ORDER_STATUS);
            preparedStatement.setString(1, orderStatus.name());
            preparedStatement.setInt(2, orderID);
            preparedStatement.setInt(3, requestID);
            preparedStatement.setInt(4, roomID);
            count=preparedStatement.executeUpdate();
            return count;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Provides the functionality of getting all client orders from a database.
     *
     * @return list of all orders.
     * @throws DaoException in case of error occurs while accessing to database.
     */
    @Override
    public ArrayList<ClientOrderRoom> getAllOrders() throws DaoException {
        ArrayList<ClientOrderRoom> clientOrderRooms = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDERS_OF_ALL_CLIENTS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                ClientOrderRoom clientOrderRoom = new ClientOrderRoom(
                        resultSet.getLong(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        resultSet.getString(7),
                        resultSet.getDate(8),
                        resultSet.getDate(9),
                        OrderStatus.valueOf(resultSet.getString(10))
                );
                clientOrderRooms.add(clientOrderRoom);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return clientOrderRooms;
    }


    /**
     * Provides a changing of the status of the specific order.
     *
     * @param orderStatus status of order to be applied.
     * @param orderID     order ID for which order status will be changed.
     * @param roomID      room ID belongs to the order ID.
     * @throws DaoException in case of error occurs while accessing to database.
     */
    @Override
    public int changeOrderStatus(String orderStatus, int orderID, int roomID) throws DaoException {
        int count;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_ORDER_STATUS_V2);
            preparedStatement.setString(1, orderStatus);
            preparedStatement.setInt(2, orderID);
            preparedStatement.setInt(3, roomID);
            count = preparedStatement.executeUpdate();
            return count;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
