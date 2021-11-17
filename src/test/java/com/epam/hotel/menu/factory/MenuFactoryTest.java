package com.epam.hotel.menu.factory;

import com.epam.hotel.entity.Menu;
import org.junit.jupiter.api.Test;

class MenuFactoryTest {

    @Test
    void getInstance() {
        Menu menu = MenuFactory.getInstance().getMenu(MenuItemDescription.ROOMS_LIST.name());

        System.out.println("menu" + menu);
    }
}