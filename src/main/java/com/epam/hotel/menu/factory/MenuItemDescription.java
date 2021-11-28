package com.epam.hotel.menu.factory;

public enum MenuItemDescription {
    MAIN("menu.main"),
    LOG_IN("menu.login"),
    SIGN_UP("menu.sign.up"),
    CLIENT_CABINET("menu.client.cabinet"),
    ADMIN_CABINET("menu.admin.cabinet"),
    LOGOUT("menu.logoff"),
    ACCOUNT("menu.account");
    String bundle;

    MenuItemDescription(String bundle) {
        this.bundle = bundle;
    }

    public String getBundle() {
        return bundle;
    }
}
