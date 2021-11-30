package com.epam.hotel.service;

import com.epam.hotel.entity.ClientOrderRoom;
import com.epam.hotel.entity.ClientRequest;
import com.epam.hotel.entity.User;
import com.epam.hotel.service.exception.ServiceException;

import java.util.ArrayList;

/**
 * Provides methods to be implemented in {@link com.epam.hotel.service.impl.ClientServiceImpl} class.
 * See description of the methods implemented:
 *
 * @see com.epam.hotel.service.impl.ClientServiceImpl#getClientOrders(User)
 * @see com.epam.hotel.service.impl.ClientServiceImpl#getClientRequests(long)
 * @see com.epam.hotel.service.impl.ClientServiceImpl#insertRequest(long, int, String, String, String)
 * @see com.epam.hotel.service.impl.ClientServiceImpl#removeRequest(int)
 * @see com.epam.hotel.service.impl.ClientServiceImpl#createRequest(int, String, String, String)
 * @see com.epam.hotel.service.impl.ClientServiceImpl#topUpAccount(long, double)
 * @see com.epam.hotel.service.impl.ClientServiceImpl#getClient(long)
 */
public interface ClientService {
    ArrayList<ClientOrderRoom> getClientOrders(User user) throws ServiceException;

    ArrayList<ClientRequest> getClientRequests(long clientID) throws ServiceException;

    void insertRequest(long clientID, int persons, String roomClass, String dateFrom, String dateTo) throws ServiceException;

    void removeRequest(int requestID) throws ServiceException;

    ClientRequest createRequest(int persons, String roomClass, String dateFrom, String dateTo) throws ServiceException;

    double topUpAccount(long clientID, double chargeAmount) throws ServiceException;

    User getClient(long clientID) throws ServiceException;

}
