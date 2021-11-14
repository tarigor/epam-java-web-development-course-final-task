package com.epam.hotel.service.factory;

import org.junit.jupiter.api.Test;

class ServiceFactoryJsonBasedTest {

    @Test
    void getService() {
        Object o = ServiceFactory.getInstance().getService(ServiceType.COMMON_SITE_ACTIVITY_SERVICE);
        System.out.println("class->" + o.getClass().getName());
    }
}