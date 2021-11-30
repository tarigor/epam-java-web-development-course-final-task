package com.epam.hotel.dao.impl;

import com.epam.hotel.dao.BaseDao;
import com.epam.hotel.dao.ClientOrderDAO;
import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.entity.ClientOrderRoom;
import com.epam.hotel.entity.ClientRequest;
import com.epam.hotel.entity.User;
import com.epam.hotel.types.OrderStatus;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Provides the functionality when working with a "client_order" table of the database.
 */
public class ClientOrderDAOImpl extends BaseDao implements ClientOrderDAO {
    private static final String GET_CLIENT_ORDERS = "" +
            "SELECT client_order_id, request_id, room_id, room_class, check_in_date, check_out_date, order_status " +
            "FROM `order` " +
            "join client_order co on `order`.client_order_id = co.client_order_room_id " +
            "join room r on `order`.room_id = r.id " +
            "join room_class rc on r.room_class_id = rc.id " +
            "where client_id = ?";
    private static final String DELETE_RECORD_FROM_TABLE = "CALL delete_record_from_order_table(?,?)";
    private static final String GET_CLIENT_REQUESTS = "" +
            "select request_id,persons_amount,room_class,check_in_date,check_out_date,request_status from request\n" +
            "join client_request cr on request.request_id = cr.client_request_id\n" +
            "where client_r_id = ?";
    private static final String DELETE_REQUEST = "DELETE FROM hotelDB.client_request WHERE client_request_id = ?";

    /**
     * Provides a getting of all orders of  the specific client.
     *
     * @param user an instance of {@link User}.
     * @return the list of the client's orders.
     * @throws DaoException in case of error occurs while accessing to database.
     */
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

    /**
     * Provides a getting of all client's requests of the specific client based on his ID.
     *
     * @param clientID client ID.
     * @return the list of all client's requests.
     * @throws DaoException in case of error occurs while accessing to database.
     */
    @Override
    public ArrayList<ClientRequest> getClientRequests(long clientID) throws DaoException {
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
            throw new DaoException(e);
        }
        return clientRequests;
    }

    /**
     * Provides a removing of the specific request based on request ID.
     *
     * @param requestID the request ID to be removed.
     * @throws DaoException in case of error occurs while accessing to database.
     */
    @Override
    public int removeRequest(int requestID) throws DaoException {
        int count;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REQUEST);
            preparedStatement.setInt(1, requestID);
            count = preparedStatement.executeUpdate();
            return count;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private ClientRequest getClientRequest(ResultSet resultSet) throws DaoException {
        try {
            return new ClientRequest(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getDate(4),
                    resultSet.getDate(5),
                    OrderStatus.valueOf(resultSet.getString(6))
            );
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

//    /**
//     * Provides a deleting of the record from the "order" table.
//     *
//     * @param order_id an order ID of record to be deleted.
//     * @param room_id  a room ID belongs to the order ID.
//     * @throws DaoException in case of error occurs while accessing to database
//     */
//    @Override
//    public void deleteRecord(int order_id, int room_id) throws DaoException {
//        int count;
//        try {
//            CallableStatement callableStatement = connection.prepareCall(DELETE_RECORD_FROM_TABLE);
//            callableStatement.setInt(1, order_id);
//            callableStatement.setInt(2, room_id);
//            callableStatement.executeQuery();
//        } catch (SQLException e) {
//            throw new DaoException(e);
//        }
//    }

    private ClientOrderRoom clientOrdersBuild(ResultSet resultSet) throws DaoException {
        try {
            return new ClientOrderRoom(
                    resultSet.getLong(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getDate(5),
                    resultSet.getDate(6),
                    OrderStatus.valueOf(resultSet.getString(7))
            );
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
