package com.epam.hotel.dao.impl;

import com.epam.hotel.dao.AdminDAO;
import com.epam.hotel.dao.BaseDao;
import com.epam.hotel.entity.ClientOrderRoom;
import com.epam.hotel.entity.ClientRequest;
import com.epam.hotel.entity.OrderStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDAOImpl extends BaseDao implements AdminDAO {
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
    private static final String CHANGE_ORDER_STATUS = "UPDATE hotelDB.`order` t SET t.order_status = ? WHERE t.client_order_id = ? AND t.room_id = ?";
    private static final String GET_ALL_REQUESTS = "" +
            "select request_id, client_r_id, first_name, last_name, email, persons_amount, room_class, check_in_date, check_out_date, request_status\n" +
            "FROM request\n" +
            "JOIN client_request cr on request.request_id = cr.client_request_id\n" +
            "JOIN user u on cr.client_r_id = u.id\n" +
            "ORDER BY request_id";
    private static final String GET_REQUEST_BY_ID_AND_EMAIL = "" +
            "select request_id, client_r_id, first_name, last_name, email, persons_amount, room_class, check_in_date, check_out_date, request_status\n" +
            "FROM request\n" +
            "JOIN client_request cr on request.request_id = cr.client_request_id\n" +
            "JOIN user u on cr.client_r_id = u.id\n" +
            "where request_id = ? and email= ?";

    @Override
    public ArrayList<ClientOrderRoom> getAllOrders() {
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
            e.printStackTrace();
        }
        return clientOrderRooms;
    }

    @Override
    public ArrayList<ClientRequest> getAllRequests() {
        ArrayList<ClientRequest> clientRequests = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_REQUESTS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                clientRequests.add(getRequest(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientRequests;
    }

    private ClientRequest getRequest(ResultSet resultSet) throws SQLException {
        return new ClientRequest(
                resultSet.getInt(1),
                resultSet.getLong(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getInt(6),
                resultSet.getString(7),
                resultSet.getDate(8),
                resultSet.getDate(9),
                OrderStatus.valueOf(resultSet.getString(10))
        );
    }

    @Override
    public void doActionWithOrder(String orderStatus, int orderID, int roomID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_ORDER_STATUS);
            preparedStatement.setString(1, orderStatus);
            preparedStatement.setInt(2, orderID);
            preparedStatement.setInt(3, roomID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ClientRequest getRequestByIDAndEmail(long requestID, String email) {
        ClientRequest clientRequest = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_REQUEST_BY_ID_AND_EMAIL);
            preparedStatement.setLong(1, requestID);
            preparedStatement.setString(2, email);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                clientRequest = getRequest(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientRequest;
    }


}
