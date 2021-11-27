package com.epam.hotel.dao.impl;

import com.epam.hotel.dao.BaseDao;
import com.epam.hotel.dao.ClientOrderDAO;
import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.entity.ClientOrderRoom;
import com.epam.hotel.entity.ClientRequest;
import com.epam.hotel.entity.OrderStatus;
import com.epam.hotel.entity.User;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientOrderDAOImpl extends BaseDao implements ClientOrderDAO {
    private static final String GET_CLIENT_ORDERS = "" +
            "SELECT client_order_id, request_id, room_id, room_class, check_in_date, check_out_date, order_status " +
            "FROM `order` " +
            "join client_order co on `order`.client_order_id = co.client_order_room_id " +
            "join room r on `order`.room_id = r.id " +
            "join room_class rc on r.room_class_id = rc.id " +
            "where client_id = ?";
    private static final String DELETE_RECORD_FROM_TABLE = "CALL delete_record_from_client_order_room(?,?)";
    private static final String GET_CLIENT_REQUESTS = "" +
            "select request_id,persons_amount,room_class,check_in_date,check_out_date,request_status from request\n" +
            "join client_request cr on request.request_id = cr.client_request_id\n" +
            "where client_r_id = ?";
    private static final String DELETE_REQUEST = "DELETE FROM hotelDB.client_request WHERE client_request_id = ?";

    @Override
    public ArrayList<ClientOrderRoom> get(User user) throws DaoException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CLIENT_ORDERS);
            preparedStatement.setLong(1, user.hashCode());
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();

            ArrayList<ClientOrderRoom> clientOrders = new ArrayList<>();

            while (resultSet.next()) {
                clientOrders.add(clientOrdersBuild(resultSet));
            }

            return clientOrders;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public ArrayList<ClientRequest> getClientRequests(long clientID) {
        ArrayList<ClientRequest> clientRequests = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CLIENT_REQUESTS);
            preparedStatement.setLong(1, clientID);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                clientRequests.add(getClientRequest(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientRequests;
    }

    @Override
    public void removeRequest(int requestID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REQUEST);
            preparedStatement.setInt(1, requestID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ClientRequest getClientRequest(ResultSet resultSet) throws SQLException {
        return new ClientRequest(
                resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getString(3),
                resultSet.getDate(4),
                resultSet.getDate(5),
                OrderStatus.valueOf(resultSet.getString(6))
        );
    }

    @Override
    public void deleteRecord(int order_id, int room_id) throws DaoException {
        try {
            CallableStatement callableStatement = connection.prepareCall(DELETE_RECORD_FROM_TABLE);
            callableStatement.setInt(1, order_id);
            callableStatement.setInt(2, room_id);
            callableStatement.executeQuery();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private ClientOrderRoom clientOrdersBuild(ResultSet resultSet) throws SQLException {
        return new ClientOrderRoom(
                resultSet.getLong(1),
                resultSet.getInt(2),
                resultSet.getInt(3),
                resultSet.getString(4),
                resultSet.getDate(5),
                resultSet.getDate(6),
                OrderStatus.valueOf(resultSet.getString(7))
        );
    }
}
