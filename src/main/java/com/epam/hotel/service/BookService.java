package com.epam.hotel.service;

public interface BookService {
    void insertNewOrder(long userID,
                        int requestID,
                        String[] singleRoomsSelected,
                        String[] doubleRoomsSelected,
                        String[] suiteRoomsSelected,
                        String[] deluxeRoomsSelected,
                        String dateFrom,
                        String dateTo);
}
