package com.epam.hotel.menu.factory;

import com.epam.hotel.entity.Menu;
import com.epam.hotel.service.exception.ServiceException;
import com.epam.hotel.utility.JsonFileHandler;
import com.google.gson.internal.LinkedTreeMap;

/**
 * Provides the functionality of getting a specific menu item based on factory pattern.
 */
public class MenuFactory {
    private static final MenuFactory instance = new MenuFactory();
    private final JsonFileHandler jsonFileHandler;

    private MenuFactory() {
        jsonFileHandler = new JsonFileHandler<Menu>("menu.json");
    }

    public static MenuFactory getInstance() {
        return instance;
    }

    /**
     * Gets an instance of {@link Menu} class initiated by data from a "menu.json" json file for specific menu item.
     *
     * @param menuItem the name of key linked with a JSON file menu.jason.
     * @return an instance of the {@link Menu} class.
     */
    public Menu getMenu(String menuItem) throws ServiceException {
        try {
            int id = Integer.parseInt(((LinkedTreeMap) jsonFileHandler.getMapOfCommandFromJson().get(menuItem)).get("id").toString());
            String pageCommandName = ((LinkedTreeMap) jsonFileHandler.getMapOfCommandFromJson().get(menuItem)).get("command").toString();
            MenuItemDescription menuItemDescription = MenuItemDescription.valueOf(((LinkedTreeMap) jsonFileHandler.getMapOfCommandFromJson().get(menuItem)).get("menuItemDescription").toString());
            MenuRole menuRole = MenuRole.valueOf(((LinkedTreeMap) jsonFileHandler.getMapOfCommandFromJson().get(menuItem)).get("role").toString());
            return new Menu(id, pageCommandName, menuItemDescription, menuRole);
        }catch (ServiceException e){
            throw new ServiceException(e);
        }
    }

}
