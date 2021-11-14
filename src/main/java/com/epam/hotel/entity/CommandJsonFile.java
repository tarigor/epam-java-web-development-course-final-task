package com.epam.hotel.entity;

/**
 * The entity of the command json file mapper which is using while getting a command by {@link com.epam.hotel.command.factory.CommandFactory}
 */
public class CommandJsonFile {
    private String clazz;
    private String method;

    public CommandJsonFile() {
    }

    public CommandJsonFile(String clazz, String method) {
        this.clazz = clazz;
        this.method = method;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
