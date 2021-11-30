package com.epam.hotel.menu.impl;

import com.epam.hotel.entity.Menu;
import com.epam.hotel.menu.SiteMenuService;
import com.epam.hotel.menu.factory.MenuFactory;
import com.epam.hotel.menu.factory.MenuItemDescription;
import com.epam.hotel.menu.factory.MenuRole;
import com.epam.hotel.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SiteMenuServiceImpl implements SiteMenuService {
    private HashMap<String, Menu> menuList;
    private static final SiteMenuServiceImpl instance = new SiteMenuServiceImpl();

    public SiteMenuServiceImpl() {
    }

    public static SiteMenuServiceImpl getInstance() {
        return instance;
    }

    public void init() throws ServiceException {
        menuList = new HashMap<>();
        MenuFactory menuFactory = MenuFactory.getInstance();
        for (MenuItemDescription menuItem : MenuItemDescription.values()) {
            menuList.put(menuItem.name(), menuFactory.getMenu(menuItem.name()));
        }
    }

    @Override
    public ArrayList<Menu> getMenuListCollectedByRoleSortedByID(MenuRole... menuRole) {
        ArrayList<Menu> sortedMenuListByRole = new ArrayList<>();
        for (MenuRole singleMenuRole : menuRole) {
            for (Map.Entry<String, Menu> entry : this.menuList.entrySet()) {
                if (entry.getValue().getRole().equals(singleMenuRole)) {
                    sortedMenuListByRole.add(entry.getValue());
                }
            }
        }
        return sortedMenuListByRole.stream()
                .sorted(Comparator.comparing(o -> ((Integer) o.getId())))
                .collect(Collectors.toCollection(ArrayList::new));
    }


}
