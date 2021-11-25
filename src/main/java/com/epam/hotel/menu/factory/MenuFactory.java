package com.epam.hotel.menu.factory;

import com.epam.hotel.entity.Menu;
import com.epam.hotel.utility.JsonFileHandler;
import com.google.gson.internal.LinkedTreeMap;

public class MenuFactory {
    private static final MenuFactory instance = new MenuFactory();
    private final JsonFileHandler jsonFileHandler;

    private MenuFactory() {
        jsonFileHandler = new JsonFileHandler<Menu>("menu.json");
    }

    public static MenuFactory getInstance() {
        return instance;
    }

    public Menu getMenu(String menuItem) {
        int id = Integer.parseInt(((LinkedTreeMap) jsonFileHandler.getMapOfCommandFromJson().get(menuItem)).get("id").toString());
        String pageCommandName = ((LinkedTreeMap) jsonFileHandler.getMapOfCommandFromJson().get(menuItem)).get("command").toString();
        MenuItemDescription menuItemDescription = MenuItemDescription.valueOf(((LinkedTreeMap) jsonFileHandler.getMapOfCommandFromJson().get(menuItem)).get("menuItemDescription").toString());
        MenuRole menuRole = MenuRole.valueOf(((LinkedTreeMap) jsonFileHandler.getMapOfCommandFromJson().get(menuItem)).get("role").toString());
        return new Menu(id, pageCommandName, menuItemDescription, menuRole);
    }

}
