package com.epam.hotel.menu;

import com.epam.hotel.entity.MenuRole;

import java.util.HashMap;
import java.util.List;

public interface SiteMenuService {
    List getMenuListCollectedByRoleSortedByID(MenuRole ...menuRole);
}
