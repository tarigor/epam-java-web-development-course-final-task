package com.epam.hotel.dao;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.entity.ClientOrderRoom;
import com.epam.hotel.entity.ClientRequest;
import com.epam.hotel.entity.User;

import java.util.ArrayList;

/**
 * Provides methods to be implemented in {@link com.epam.hotel.dao.impl.ClientOrderDAOImpl} class.
 * See description of the methods implemented:
 *
// * @see com.epam.hotel.dao.impl.ClientOrderDAOImpl#deleteRecord(int, int)
 * @see com.epam.hotel.dao.impl.ClientOrderDAOImpl#get(User)
 * @see com.epam.hotel.dao.impl.ClientOrderDAOImpl#getClientRequests(long)
 * @see com.epam.hotel.dao.impl.ClientOrderDAOImpl#removeRequest(int)
 */
public interface ClientOrderDAO {

//    int deleteRecord(int order_id, int room_id) throws DaoException;

    ArrayList<ClientOrderRoom> get(User user) throws DaoException;

    ArrayList<ClientRequest> getClientRequests(long clientID) throws DaoException;

    int removeRequest(int requestID) throws DaoException;
}
