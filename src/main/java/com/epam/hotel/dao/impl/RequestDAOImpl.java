package com.epam.hotel.dao.impl;

import com.epam.hotel.dao.BaseDao;
import com.epam.hotel.dao.IRequestDAO;
import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.entity.ClientRequest;
import com.epam.hotel.types.OrderStatus;

import java.sql.*;
import java.util.ArrayList;

/**
 * Provides the functionality when working with a "request" table of the database.
 */
public class RequestDAOImpl extends BaseDao implements IRequestDAO {
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
    private static final String INSERT_NEW_REQUEST = "call insert_new_request(?,?,?,?,?,?)";
    private static final String CHANGE_REQUEST_STATUS = "UPDATE request t SET t.request_status = ? WHERE t.request_id = ?";

    /**
     * Provides the functionality of getting of all client requests from the database.
     *
     * @return list of all requests.
     * @throws DaoException in case of error occurs while accessing to database.
     */
    @Override
    public ArrayList<ClientRequest> getAllRequests() throws DaoException {
        ArrayList<ClientRequest> clientRequests = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_REQUESTS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                clientRequests.add(getRequest(resultSet));
            }
            return clientRequests;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private ClientRequest getRequest(ResultSet resultSet) throws DaoException {
        try {
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
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Provides a getting request data based on a specific request ID and an email.
     *
     * @param requestID request Id for which data to be received.
     * @param email     belongs to the specific request ID.
     * @return an instance of {@link ClientRequest} class.
     * @throws DaoException in case of error occurs while accessing to database.
     */
    @Override
    public ClientRequest getRequestByIDAndEmail(long requestID, String email) throws DaoException {
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
            throw new DaoException(e);
        }
        return clientRequest;
    }

    /**
     * Provides an inserting a new request.
     *
     * @param clientID  a client ID belongs to the request.
     * @param persons   the persons amount belongs to the request.
     * @param roomClass a room class belongs to the request.
     * @param dateFrom  a date from which room has requested.
     * @param dateTo    a date till which room has requested.
     * @throws DaoException in case of error occurs while accessing to database.
     */
    @Override
    public int insertRequest(long clientID, int persons, String roomClass, Date dateFrom, Date dateTo) throws DaoException {
        int requestID = 0;
        try (CallableStatement callableStatement = connection.prepareCall(INSERT_NEW_REQUEST)) {
            callableStatement.setLong(1, clientID);
            callableStatement.setInt(2, persons);
            callableStatement.setString(3, roomClass);
            callableStatement.setDate(4, dateFrom);
            callableStatement.setDate(5, dateTo);
            callableStatement.setString(6, OrderStatus.WAITING_FOR_APPROVAL.name());
            callableStatement.executeQuery();
            ResultSet resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                requestID = resultSet.getInt(1);
            }
            return requestID;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public int changeRequestStatus(Integer requestID) throws DaoException {
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_REQUEST_STATUS);
            preparedStatement.setString(1, OrderStatus.REJECTED.name());
            preparedStatement.setInt(2, requestID);
            count = preparedStatement.executeUpdate();
            return count;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
