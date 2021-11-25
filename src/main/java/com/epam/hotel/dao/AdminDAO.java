package com.epam.hotel.dao;

import com.epam.hotel.entity.ClientOrderRoom;

import java.util.ArrayList;

public interface AdminDAO {
    ArrayList<ClientOrderRoom> getAllOrdersOfAllClients();

    void doActionWithOrder(String orderStatus, int orderID, int roomID);
}
