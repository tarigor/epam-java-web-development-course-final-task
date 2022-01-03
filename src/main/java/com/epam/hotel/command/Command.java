package com.epam.hotel.command;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Provides the single method - execute{@link Command#execute(HttpServletRequest, HttpServletResponse)}.
 */
public interface Command {
    void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, DaoException;
}
