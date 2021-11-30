package com.epam.hotel.dao.impl;

import com.epam.hotel.dao.BaseDao;
import com.epam.hotel.dao.UserDAO;
import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.entity.User;
import com.epam.hotel.types.OrderStatus;
import com.epam.hotel.types.UserType;

import java.sql.*;

/**
 * Provides the functionality when working with a "user" table of the database.
 */
public class UserDAOImpl extends BaseDao implements UserDAO {
    private static final String INSERT_NEW_REQUEST = "call insert_new_request(?,?,?,?,?,?)";
    private static final String MODIFY_ACCOUNT = "call charge_account(?, ?)";
    private final String INSERT_NEW_USER =
            "INSERT INTO `user` (`id`,`first_name`, `last_name`, `user_type`, `email`, `password`,account) VALUES(?,?,?,?,?,?,?)";
    private final String GET_USER_BY_ID =
            "SELECT * FROM user WHERE id = ?";

    /**
     * Provides a checking of user for existing in a database.
     *
     * @param userHashCode user hashcode as an ID in th database.
     * @return a hashcode if existing or 0 value if not existing.
     * @throws DaoException in case of error occurs while accessing to database.
     */
    @Override
    public long checkIfUserExist(int userHashCode) throws DaoException {
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
            throw new DaoException(e);
        }
        return 0;
    }

    /**
     * Provides an insertion of the new user in a database.
     *
     * @param user an instance of {@link User} class initiated with data of new user.
     * @throws DaoException DaoException in case of error occurs while accessing to database.
     */
    @Override
    public int insert(User user) throws DaoException {
        int count;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_USER);
            preparedStatement.setLong(1, user.hashCode());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getUserType().name());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setDouble(7, 0.0);
            count = preparedStatement.executeUpdate();
            return count;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Provides a getting of user based on requested user id.
     *
     * @param userHashCode user ID to be requested from a database.
     * @return an instance of {@link User} class if existing or null if not exist.
     * @throws DaoException in case of error occurs while accessing to database.
     */
    @Override
    public User get(long userHashCode) throws DaoException {
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
            throw new DaoException(e);
        }
        return null;
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
        try(CallableStatement callableStatement = connection.prepareCall(INSERT_NEW_REQUEST)) {
            callableStatement.setLong(1, clientID);
            callableStatement.setInt(2, persons);
            callableStatement.setString(3, roomClass);
            callableStatement.setDate(4, dateFrom);
            callableStatement.setDate(5, dateTo);
            callableStatement.setString(6, OrderStatus.WAITING_FOR_APPROVAL.name());
            callableStatement.executeQuery();
            ResultSet resultSet = callableStatement.getResultSet();
            while (resultSet.next()){
                requestID = resultSet.getInt(1);
            }
            return requestID;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Provides a modification of the "account" column of the "user" table of the specific user.
     *
     * @param clientID     a client ID for which a modification will be applied.
     * @param chargeAmount an amount to which account will be modified.
     * @return a result of the modified account.
     * @throws DaoException in case of error occurs while accessing to database.
     */
    @Override
    public double modifyAccount(long clientID, double chargeAmount) throws DaoException {
        double account = 0.0;
        try {
            CallableStatement callableStatement = connection.prepareCall(MODIFY_ACCOUNT);
            callableStatement.setLong(1, clientID);
            callableStatement.setDouble(2, chargeAmount);
            callableStatement.executeQuery();
            ResultSet resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                account = resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return account;
    }
}
