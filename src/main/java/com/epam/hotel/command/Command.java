package com.epam.hotel.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

/**
 * Common interface of the commands provides a single method - execute.
 */
public interface Command {
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException;
}
