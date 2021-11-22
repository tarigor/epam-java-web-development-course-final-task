package com.epam.hotel.entity;

import com.epam.hotel.menu.factory.MenuItemDescription;
import com.epam.hotel.menu.factory.MenuRole;

public class Menu {
    private int id;
    private String command;
    private MenuItemDescription menuItemDescription;
    private MenuRole menuRole;

    public Menu(int id, String command, MenuItemDescription menuItemDescription, MenuRole menuRole) {
        this.id = id;
        this.command = command;
        this.menuItemDescription = menuItemDescription;
        this.menuRole = menuRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public MenuItemDescription getMenuItemDescription() {
        return menuItemDescription;
    }

    public void setMenuItemDescription(MenuItemDescription menuItemDescription) {
        this.menuItemDescription = menuItemDescription;
    }

    public MenuRole getMenuRole() {
        return menuRole;
    }

    public void setMenuRole(MenuRole menuRole) {
        this.menuRole = menuRole;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", pageCommandName='" + command + '\'' +
                ", menuItemDescription=" + menuItemDescription +
                ", menuRole=" + menuRole +
                '}';
    }
}
