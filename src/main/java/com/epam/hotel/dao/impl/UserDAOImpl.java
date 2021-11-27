package com.epam.hotel.dao.impl;

import com.epam.hotel.dao.BaseDao;
import com.epam.hotel.dao.UserDAO;
import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.entity.OrderStatus;
import com.epam.hotel.entity.User;
import com.epam.hotel.entity.UserType;

import java.sql.*;

/**
 * The class provides the methods of the user table handling.
 */
public class UserDAOImpl extends BaseDao implements UserDAO {
    private static final String INSERT_NEW_REQUEST = "call insert_new_request(?,?,?,?,?,?)";
    private final String INSERT_NEW_USER =
            "INSERT INTO `user` (`id`,`first_name`, `last_name`, `user_type`, `email`, `password`,account) VALUES(?,?,?,?,?,?,?)";
    private final String GET_USER_BY_ID =
            "SELECT * FROM user WHERE id = ?";

    @Override
    public long checkIfUserExist(int userHashCode) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID);
            preparedStatement.setLong(1, userHashCode);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();

            if (resultSet.next()) {
                User user = new User();
                user.setEmail(resultSet.getString(5));
                return user.hashCode();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insert(User user) throws DaoException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_USER);
            preparedStatement.setLong(1, user.hashCode());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getUserType().name());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPassword());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public User get(int userHashCode) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID);
            preparedStatement.setLong(1, userHashCode);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();

            if (resultSet.next()) {
                User user = new User();
                user.setUserID(resultSet.getLong(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setUserType(UserType.valueOf(resultSet.getString(4)));
                user.setEmail(resultSet.getString(5));
                user.setPassword(resultSet.getString(6));
                user.setAccount(resultSet.getDouble(7));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insertRequest(long clientID, int persons, String roomClass, Date dateFrom, Date dateTo) {
        int request_id = 0;
        try {
            CallableStatement callableStatement = connection.prepareCall(INSERT_NEW_REQUEST);
            callableStatement.setLong(1, clientID);
            callableStatement.setInt(2, persons);
            callableStatement.setString(3, roomClass);
            callableStatement.setDate(4, dateFrom);
            callableStatement.setDate(5, dateTo);
            callableStatement.setString(6, OrderStatus.WAITING_FOR_APPROVAL.name());
            callableStatement.executeQuery();
            ResultSet resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                request_id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
