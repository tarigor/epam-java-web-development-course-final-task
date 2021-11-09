package com.epam.hotel.command.factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryTest {

    @Test
    void getCommand() {
        Class o = CommandFactory.getInstance().getCommand("login");
        System.out.println("class->"+o.getName());
    }
}