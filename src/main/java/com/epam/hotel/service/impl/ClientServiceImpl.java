package com.epam.hotel.service.impl;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.dao.factory.DAOServiceFactory;
import com.epam.hotel.dao.factory.DAOType;
import com.epam.hotel.dao.impl.ClientOrderDAOImpl;
import com.epam.hotel.entity.ClientOrderRoom;
import com.epam.hotel.entity.User;
import com.epam.hotel.service.BaseService;
import com.epam.hotel.service.ClientService;

import java.util.ArrayList;

public class ClientServiceImpl extends BaseService implements ClientService {
    private final ClientOrderDAOImpl clientOrderDAO =
            (ClientOrderDAOImpl) DAOServiceFactory.getInstance().getDAO(DAOType.CLIENT_ORDER_DAO);

    public ClientServiceImpl() {
    }

    @Override
    public ArrayList<ClientOrderRoom> getClientOrders(User user) {
        ArrayList<ClientOrderRoom> clientOrders = transaction.createConnection().performTransaction(() -> clientOrderDAO.get(user));
        clientOrders.forEach(System.out::println);
        for (ClientOrderRoom order : clientOrders) {
            System.out.println("canBeCanceled->" + order.isCanBeCanceled());
        }
        return clientOrders;
    }

    public void removeOrderFromBooking(int orderID, int roomID) {
        transaction.createConnection().performTransaction(() -> {
            try {
                clientOrderDAO.deleteRecord(orderID, roomID);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        });

    }
}
