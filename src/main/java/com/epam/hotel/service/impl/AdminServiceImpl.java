package com.epam.hotel.service.impl;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.dao.factory.DAOServiceFactory;
import com.epam.hotel.dao.factory.DAOType;
import com.epam.hotel.dao.impl.OrderDAOImpl;
import com.epam.hotel.dao.impl.RequestDAOImpl;
import com.epam.hotel.entity.ClientOrderRoom;
import com.epam.hotel.entity.ClientRequest;
import com.epam.hotel.service.AdminService;
import com.epam.hotel.service.BaseService;
import com.epam.hotel.service.exception.ServiceException;
import com.epam.hotel.types.OrderStatus;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Provides the functionality of the admin activities.
 */
public class AdminServiceImpl extends BaseService implements AdminService {
    public static final String APPROVE = "approve";
    public static final String REJECT = "reject";
    private static final Logger LOGGER = Logger.getLogger(AdminServiceImpl.class);
    private final OrderDAOImpl orderDAO = (OrderDAOImpl) DAOServiceFactory.getInstance().getDaoObjectMap().get(DAOType.ORDER_DAO);
    private final RequestDAOImpl requestDAO = (RequestDAOImpl) DAOServiceFactory.getInstance().getDaoObjectMap().get(DAOType.REQUEST_DAO);

    /**
     * Gets of the all orders from a database.
     *
     * @return a list of all orders.
     */
    @Override
    public ArrayList<ClientOrderRoom> getAllOrders() throws ServiceException {
        try {
            return transaction.createConnection().performTransaction(() -> orderDAO.getAllOrders());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Gets of all requests from a database.
     *
     * @return a list of all requests.
     */
    @Override
    public ArrayList<ClientRequest> getAllRequests() throws ServiceException {
        try {
            return transaction.createConnection().performTransaction(() -> requestDAO.getAllRequests());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Changes an order's status of the specific an order.
     *
     * @param statusType a name of status.
     * @param orderID    an order ID for which status to be changed.
     * @param roomID     an room ID belongs to an order ID.
     */
    @Override
    public void changeOrderStatusAdminAction(String statusType, int orderID, int roomID) throws ServiceException {
        try {
            if (statusType.contains(APPROVE)) {
                transaction.createConnection().performTransaction(() -> orderDAO.changeOrderStatus(OrderStatus.APPROVED_WAITING_FOR_PAYMENT.name(), orderID, roomID));
            } else if (statusType.contains(REJECT)) {
                transaction.createConnection().performTransaction(() -> orderDAO.changeOrderStatus(OrderStatus.REJECTED.name(), orderID, roomID));
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Gets a data of the specific request from the the database based on request ID.
     *
     * @param requestID a request ID for which data will get from the database.
     * @param email     an email belongs to the specific request.
     * @return n instance of {@link ClientRequest} class consists data of the request.
     */
    @Override
    public ClientRequest getRequest(long requestID, String email) throws ServiceException {
        try {
            return transaction.createConnection().performTransaction(() -> requestDAO.getRequestByIDAndEmail(requestID, email));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


}
