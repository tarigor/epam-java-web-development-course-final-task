package com.epam.hotel.command.factory;

/**
 * ENUMs of the all types of the commands.
 */
public enum CommandType {
    LOGIN,
    LOGOUT,
    USER_REGISTRATION,
    LOGOFF,
    REMOVE_ROOM_FROM_BOOKING,
    CHECK_FREE_ROOM,
    SEND_INVOICE,
    ORDER_HANDLING,
    REQUEST,
    CANCEL_REQUEST,
    REQUEST_HANDLING,
    ADMIN_CABINET,
    CLIENT_CABINET,
    MAKE_PAYMENT,
    ACCOUNT,
    TOP_UP,
    PAY;
}
