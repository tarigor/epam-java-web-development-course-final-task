package com.epam.hotel.service.factory;

import com.epam.hotel.service.Service;
import com.epam.hotel.service.ServiceType;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    public ServiceFactory getInstance() {
        return instance;
    }

    public Service getService(ServiceType serviceType) {
        return serviceType.getService();
    }
}
