package com.epam.hotel.menu.impl;

import com.epam.hotel.entity.Menu;
import com.epam.hotel.menu.factory.MenuRole;
import com.epam.hotel.service.exception.ServiceException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class SiteMenuServiceImplTest {

    @Test
    void getMenuListSortedByRole() throws ServiceException {
        SiteMenuServiceImpl siteMenuService = new SiteMenuServiceImpl();
        ArrayList<Menu> testList = new ArrayList<>();
        testList = SiteMenuServiceImpl.getInstance().getMenuListCollectedByRoleSortedByID(MenuRole.COMMON, MenuRole.ANYONE_NOT_LOGGED);
        for (Menu menu : testList) {
            System.out.println(menu.toString());
        }
    }
}