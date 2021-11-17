package com.epam.hotel.menu.impl;

import com.epam.hotel.entity.Menu;
import com.epam.hotel.menu.SiteMenuService;
import com.epam.hotel.menu.factory.MenuFactory;
import com.epam.hotel.menu.factory.MenuItemDescription;
import com.epam.hotel.menu.factory.MenuRole;
import com.epam.hotel.service.factory.ServiceFactory;
import com.epam.hotel.service.factory.ServiceType;
import com.epam.hotel.service.impl.PropertiesFileServiceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SiteMenuServiceImpl implements SiteMenuService {
    private final PropertiesFileServiceImpl propertiesFileService =
            (PropertiesFileServiceImpl) ServiceFactory.getInstance().getService(ServiceType.PROPERTIES_FILE_SERVICE);
    private HashMap<String, Menu> menuList = new HashMap<>();

    public SiteMenuServiceImpl() {
        init(menuList);
    }

    private void init(HashMap<String, Menu> menuList) {
        MenuFactory menuFactory = MenuFactory.getInstance();
        for (MenuItemDescription menuItem : MenuItemDescription.values()) {
            menuList.put(menuItem.name(), menuFactory.getMenu(menuItem.name()));
        }
        this.menuList = menuList;
    }

    @Override
    public ArrayList<Menu> getMenuListCollectedByRoleSortedByID(MenuRole... menuRole) {
        ArrayList<Menu> sortedMenuListByRole = new ArrayList<>();
        for (MenuRole singleMenuRole : menuRole) {
            for (Map.Entry<String, Menu> entry : this.menuList.entrySet()) {
                if (entry.getValue().getMenuRole().equals(singleMenuRole)) {
                    sortedMenuListByRole.add(entry.getValue());
                }
            }
        }
        return sortedMenuListByRole.stream()
                .sorted(Comparator.comparing(o -> ((Integer) o.getId())))
                .collect(Collectors.toCollection(ArrayList::new));
    }


}
