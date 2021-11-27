package com.epam.hotel.dao;

import com.epam.hotel.entity.ClientOrderRoom;
import com.epam.hotel.entity.ClientRequest;

import java.util.ArrayList;

public interface AdminDAO {
    ArrayList<ClientOrderRoom> getAllOrders();
    ArrayList<ClientRequest> getAllRequests();
    void doActionWithOrder(String orderStatus, int orderID, int roomID);
    ClientRequest getRequestByIDAndEmail(long requestID,String email);
}
