package com.epam.hotel.service;

import com.epam.hotel.entity.ClientOrderRoom;
import com.epam.hotel.entity.ClientRequest;
import com.epam.hotel.entity.User;

import java.util.ArrayList;

public interface ClientService {
    ArrayList<ClientOrderRoom> getClientOrders(User user);

    ArrayList<ClientRequest> getClientRequests(long clientID);

    void insertRequest(long clientID, int persons, String roomClass, String dateFrom, String dateTo);

    void removeRequest(int requestID);

    ClientRequest createRequest(int persons, String roomClass, String dateFrom, String dateTo);

    double chargeAccount(long clientID, double chargeAmount);

    User getClient(long clientID);

}
