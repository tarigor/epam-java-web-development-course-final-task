package com.epam.hotel.command;

import com.epam.hotel.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

/**
 * Provides the single method - execute{@link Command#execute(HttpServletRequest, HttpServletResponse)}.
 */
public interface Command {
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException, ServiceException;
}
