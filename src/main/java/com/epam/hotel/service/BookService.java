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
    void payInvoice(long userID, int orderID,int requestID, int roomID, double roomPrice);
}
