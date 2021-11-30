package com.epam.hotel.menu;

import com.epam.hotel.menu.factory.MenuRole;

import java.util.List;

/**
 * Provides methods to be implemented in {@link com.epam.hotel.menu.impl.SiteMenuServiceImpl} class.
 * See description of the method implemented:
 *
 * @see com.epam.hotel.menu.impl.SiteMenuServiceImpl#getMenuListCollectedByRoleSortedByID(MenuRole...)
 */
public interface SiteMenuService {
    List getMenuListCollectedByRoleSortedByID(MenuRole... menuRole);
}
