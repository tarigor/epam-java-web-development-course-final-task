package com.epam.hotel.service;

import com.epam.hotel.entity.ClientOrderRoom;
import com.epam.hotel.entity.User;

import java.util.ArrayList;

public interface ClientService {
    ArrayList<ClientOrderRoom> getClientOrders(User user);
}
