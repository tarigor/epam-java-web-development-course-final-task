package com.epam.hotel.service;

import com.epam.hotel.entity.ClientOrderRoom;
import com.epam.hotel.entity.ClientRequest;

import java.util.ArrayList;

public interface AdminService {
    ArrayList<ClientOrderRoom> getAllOrders();
    ArrayList<ClientRequest>getAllRequests();
    void doActionWithOrder(String actionType, int orderID, int roomID);
    ClientRequest getRequest(long requestID, String email);
}
