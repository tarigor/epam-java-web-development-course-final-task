package com.epam.hotel.dao;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.entity.ClientOrderRoom;
import com.epam.hotel.entity.ClientRequest;
import com.epam.hotel.entity.User;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface ClientOrderDAO {

    void deleteRecord(int order_id, int room_id) throws DaoException;
    ArrayList<ClientOrderRoom> get(User user) throws DaoException;
    ArrayList<ClientRequest> getClientRequests(long clientID);
    void removeRequest(int requestID);
}
