package com.epam.hotel.dao;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.dao.impl.ClientOrderDAOImpl;
import com.epam.hotel.entity.ClientOrderRoom;
import com.epam.hotel.entity.ClientRequest;
import com.epam.hotel.entity.User;

import java.util.ArrayList;

/**
 * Provides methods to be implemented in {@link ClientOrderDAOImpl} class.
 * See description of the methods implemented:
 * <p>
 * // * @see com.epam.hotel.dao.impl.ClientOrderDAOImpl#deleteRecord(int, int)
 *
 * @see ClientOrderDAOImpl#get(User)
 * @see ClientOrderDAOImpl#getClientRequests(long)
 * @see ClientOrderDAOImpl#removeRequest(int)
 */
public interface IClientOrderDAO {

    ArrayList<ClientOrderRoom> get(User user) throws DaoException;

    ArrayList<ClientRequest> getClientRequests(long clientID) throws DaoException;

    int removeRequest(int requestID) throws DaoException;
}
