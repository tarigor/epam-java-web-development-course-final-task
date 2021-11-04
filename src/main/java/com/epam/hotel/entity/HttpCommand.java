package com.epam.hotel.entity;

/**
 * @author Igor Taren
 */
public class HttpCommand {
    private Class clazz;
    private String method;

    public HttpCommand(Class clazz, String method) {
        this.clazz = clazz;
        this.method = method;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
