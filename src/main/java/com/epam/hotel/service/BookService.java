package com.epam.hotel.service;

public interface BookService {
    void insertNewOrder(long userID,
                        String[] singleRoomsSelected,
                        String[] doubleRoomsSelected,
                        String[] suiteRoomsSelected,
                        String[] deluxeRoomsSelected,
                        String dateFrom,
                        String dateTo);
}
