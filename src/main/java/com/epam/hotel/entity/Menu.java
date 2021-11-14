package com.epam.hotel.entity;

public class Menu {
    private int id;
    private String pageCommandName;
    private MenuItemDescription menuItemDescription;
    private MenuRole menuRole;

    public Menu(int id, String pageCommandName, MenuItemDescription menuItemDescription, MenuRole menuRole) {
        this.id = id;
        this.pageCommandName = pageCommandName;
        this.menuItemDescription = menuItemDescription;
        this.menuRole = menuRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPageCommandName() {
        return pageCommandName;
    }

    public void setPageCommandName(String pageCommandName) {
        this.pageCommandName = pageCommandName;
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
                ", pageCommandName='" + pageCommandName + '\'' +
                ", menuItemDescription=" + menuItemDescription +
                ", menuRole=" + menuRole +
                '}';
    }
}
