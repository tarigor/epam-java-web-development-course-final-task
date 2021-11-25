package com.epam.hotel.service.impl;

import com.epam.hotel.dao.factory.DAOServiceFactory;
import com.epam.hotel.dao.factory.DAOType;
import com.epam.hotel.dao.impl.AdminDAOImpl;
import com.epam.hotel.entity.ClientOrderRoom;
import com.epam.hotel.entity.OrderStatus;
import com.epam.hotel.service.AdminService;
import com.epam.hotel.service.BaseService;

import java.util.ArrayList;

public class AdminServiceImpl extends BaseService implements AdminService {
    private AdminDAOImpl adminDAO = (AdminDAOImpl) DAOServiceFactory.getInstance().getDAO(DAOType.ADMIN_DAO);

    @Override
    public ArrayList<ClientOrderRoom> getAllOrdersOfAllClients() {
        return transaction.createConnection().performTransaction(() -> adminDAO.getAllOrdersOfAllClients());
    }

    @Override
    public void doActionWithOrder(String actionType, int orderID, int roomID) {
        if (actionType.contains("approve")) {
            transaction.createConnection().performTransaction(() -> adminDAO.doActionWithOrder(OrderStatus.APPROVED_AND_BOOKED.name(), orderID, roomID));
        } else if (actionType.contains("reject")) {
            transaction.createConnection().performTransaction(() -> adminDAO.doActionWithOrder(OrderStatus.REJECTED.name(), orderID, roomID));
        }
    }


}
