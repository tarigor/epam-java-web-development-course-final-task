package com.epam.hotel.service;

import com.epam.hotel.entity.ClientOrder;
import com.epam.hotel.entity.User;

import java.util.ArrayList;

public interface ClientService {
    ArrayList<ClientOrder> getClientOrders(User user);
}
