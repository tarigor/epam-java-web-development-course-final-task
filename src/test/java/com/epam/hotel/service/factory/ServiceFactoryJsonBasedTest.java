package com.epam.hotel.service.factory;

import com.epam.hotel.service.Service;
import org.junit.jupiter.api.Test;

class ServiceFactoryJsonBasedTest {

    @Test
    void getService() {
        Object o = ServiceFactory.getInstance().getService(Service.COMMON_SITE_ACTIVITY_SERVICE);
        System.out.println("class->"+o.getClass().getName());
    }
}