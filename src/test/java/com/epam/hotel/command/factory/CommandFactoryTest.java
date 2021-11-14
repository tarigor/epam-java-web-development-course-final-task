package com.epam.hotel.command.factory;

import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

class CommandFactoryTest {

    @Test
    void getCommand() {
        Class o = CommandFactory.getInstance().getCommand("login");
        System.out.println("class->" + o.getName());


        ResourceBundle messages = ResourceBundle.getBundle("webtext");
        System.out.println(messages.getString("menu.main"));
    }
}