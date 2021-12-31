package com.epam.hotel.service;

import com.epam.hotel.entity.ClientOrderRoom;
import com.epam.hotel.entity.ClientRequest;
import com.epam.hotel.service.exception.ServiceException;
import com.epam.hotel.service.impl.AdminServiceImpl;

import java.util.ArrayList;

/**
 * Provides methods to be implemented in {@link com.epam.hotel.service.impl.AdminServiceImpl} class.
 * See description of the methods implemented:
 *
 * @see AdminServiceImpl#getAllOrders()
 * @see AdminServiceImpl#getAllRequests()
 * @see com.epam.hotel.service.impl.AdminServiceImpl#changeOrderStatusAdminAction(String, int, int)
 * @see com.epam.hotel.service.impl.AdminServiceImpl#getRequest(long, String)
 * @see com.epam.hotel.service.impl.AdminServiceImpl#rejectRequest(Integer)
 */
public interface IAdminService {
    ArrayList<ClientOrderRoom> getAllOrders() throws ServiceException;

    ArrayList<ClientRequest> getAllRequests() throws ServiceException;

    void changeOrderStatusAdminAction(String actionType, int orderID, int roomID) throws ServiceException;

    ClientRequest getRequest(long requestID, String email) throws ServiceException;

    Integer rejectRequest(Integer requestID) throws ServiceException;
}
