package com.epam.hotel.service;

import com.epam.hotel.service.exception.ServiceException;

/**
 * Provides methods to be implemented in {@link com.epam.hotel.service.impl.BookServiceImpl} class.
 * See description of the methods implemented:
 *
 * @see com.epam.hotel.service.impl.BookServiceImpl#insertNewOrder(long, int, String[], String[], String[], String[], String, String)
 * @see com.epam.hotel.service.impl.BookServiceImpl#payInvoice(long, int, int, int, double)
 */
public interface BookService {
    int insertNewOrder(long userID,
                       int requestID,
                       String[] singleRoomsSelected,
                       String[] doubleRoomsSelected,
                       String[] suiteRoomsSelected,
                       String[] deluxeRoomsSelected,
                       String dateFrom,
                       String dateTo) throws ServiceException;

    void payInvoice(long userID, int orderID, int requestID, int roomID, double roomPrice) throws ServiceException;
}
