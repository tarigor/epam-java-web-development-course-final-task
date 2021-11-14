package com.epam.hotel.entity;

public enum MenuItemDescription {
    MAIN("menu.main"),
    ROOMS_LIST("menu.rooms.list"),
    LOG_IN("menu.login"),
    SIGN_UP("menu.sign.up"),
    CLIENT_CABINET("menu.client.cabinet"),
    ADMIN_CABINET("menu.admin.cabinet"),
    LOGOFF("menu.logoff");
    String bundle;

    MenuItemDescription(String bundle) {
        this.bundle = bundle;
    }

    public String getBundle() {
        return bundle;
    }
}
