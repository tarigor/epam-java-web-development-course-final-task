package com.epam.hotel.dao;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.dao.impl.RequestDAOImpl;
import com.epam.hotel.entity.ClientRequest;

import java.util.ArrayList;

/**
 * Provides methods to be implemented in {@link com.epam.hotel.dao.impl.RequestDAOImpl} class.
 * See description of the methods implemented:
 *
 * @see RequestDAOImpl#getAllRequests()
 * @see com.epam.hotel.dao.impl.RequestDAOImpl#getRequestByIDAndEmail(long, String)
 */
public interface RequestDAO {

    ArrayList<ClientRequest> getAllRequests() throws DaoException;


    ClientRequest getRequestByIDAndEmail(long requestID, String email) throws DaoException;
}
