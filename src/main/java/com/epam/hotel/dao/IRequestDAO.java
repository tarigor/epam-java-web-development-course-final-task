package com.epam.hotel.dao;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.dao.impl.RequestDAOImpl;
import com.epam.hotel.entity.ClientRequest;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Provides methods to be implemented in {@link RequestDAOImpl} class.
 * See description of the methods implemented:
 *
 * @see RequestDAOImpl#getAllRequests()
 * @see RequestDAOImpl#insertRequest(long, int, String, Date, Date)
 * @see RequestDAOImpl#getRequestByIDAndEmail(long, String)
 */
public interface IRequestDAO {

    ArrayList<ClientRequest> getAllRequests() throws DaoException;

    ClientRequest getRequestByIDAndEmail(long requestID, String email) throws DaoException;

    int insertRequest(long clientID, int persons, String roomClass, Date dateFrom, Date dateTo) throws DaoException;
}
