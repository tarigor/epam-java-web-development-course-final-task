package com.epam.hotel.dao.impl;

import com.epam.hotel.dao.BaseDao;
import com.epam.hotel.dao.ClientOrderDAO;
import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.entity.ClientOrderRoom;
import com.epam.hotel.entity.OrderStatus;
import com.epam.hotel.entity.User;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientOrderDAOImpl extends BaseDao implements ClientOrderDAO {
    private static final String GET_CLIENT_ORDERS = "" +
            "SELECT client_order_id, room_id, room_class, check_in_date, check_out_date, order_status " +
            "FROM client_order_room " +
            "join client_order co on client_order_room.client_order_id = co.client_order_room_id " +
            "join room r on client_order_room.room_id = r.id " +
            "join room_class rc on r.room_class_id = rc.id " +
            "where client_id = ?";
    private static final String DELETE_RECORD_FROM_TABLE = "CALL delete_record_from_client_order_room(?,?)";

    @Override
    public int insert(User user) throws DaoException {
        return 0;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User get(String string) {
        return null;
    }

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
                resultSet.getString(3),
                resultSet.getDate(4),
                resultSet.getDate(5),
                OrderStatus.valueOf(resultSet.getString(6))
        );
    }
}
