package com.epam.hotel.menu;

import com.epam.hotel.menu.factory.MenuRole;

import java.util.List;

public interface SiteMenuService {
    List getMenuListCollectedByRoleSortedByID(MenuRole... menuRole);
}
