package com.epam.hotel.menu.impl;

import com.epam.hotel.entity.Menu;
import com.epam.hotel.menu.factory.MenuRole;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class SiteMenuServiceImplTest {

    @Test
    void getMenuListSortedByRole() {
        SiteMenuServiceImpl siteMenuService = new SiteMenuServiceImpl();
        ArrayList<Menu> testList = new ArrayList<>();
        testList = siteMenuService.getMenuListCollectedByRoleSortedByID(MenuRole.COMMON, MenuRole.ANYONE_NOT_LOGGED);
        for (Menu menu : testList) {
            System.out.println(menu.toString());
        }
    }
}