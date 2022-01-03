package com.epam.hotel.controller;

import com.epam.hotel.command.Command;
import com.epam.hotel.command.factory.CommandFactory;
import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorHandlerController extends HttpServlet {
    public static final String ERROR_JSP = "/WEB-INF/jsp/error.jsp";
    public static final String ERROR_MESSAGE = "errorMessage";
    private static final String ERROR_COMMAND = "ERROR";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processError(req, resp);
    }

    private void processError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class commandClass = CommandFactory.getInstance().getCommand(ERROR_COMMAND);
            ((Command) commandClass.newInstance()).execute(request, response);
        } catch (IOException | IllegalAccessException | InstantiationException | ServiceException | DaoException e) {
            request.setAttribute(ERROR_MESSAGE, e.getMessage());
            request.getRequestDispatcher(request.getContextPath() + ERROR_JSP).forward(request, response);
        }
    }
}
