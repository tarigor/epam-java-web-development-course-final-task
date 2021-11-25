package com.epam.hotel.service;

import com.epam.hotel.entity.ClientOrderRoom;

import java.util.ArrayList;

public interface AdminService {
    ArrayList<ClientOrderRoom> getAllOrdersOfAllClients();

    void doActionWithOrder(String actionType, int orderID, int roomID);
}
