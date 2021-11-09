package com.epam.hotel.service.impl;

public class CommonSiteActivityServiceImpl {
    private static final CommonSiteActivityServiceImpl instance = new CommonSiteActivityServiceImpl();

    public CommonSiteActivityServiceImpl() {
    }

    public static CommonSiteActivityServiceImpl getInstance(){
        return instance;
    }
}
