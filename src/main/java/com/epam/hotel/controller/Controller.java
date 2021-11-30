package com.epam.hotel.controller;

import com.epam.hotel.command.Command;
import com.epam.hotel.command.factory.CommandFactory;
import com.epam.hotel.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Provides the functionality of the front-controller.
 */
public class Controller extends HttpServlet {
    public static final String COMMAND_NAME = "name";
    private static final Logger logger = Logger.getLogger(Controller.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandling(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandling(request, response);
    }

    private void doHandling(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = (String) request.getAttribute(COMMAND_NAME);
        logger.info(String.format("the following command detected - /%s", command));

        Class commandClass = CommandFactory.getInstance().getCommand(command);
        try {
            ((Command) commandClass.newInstance()).execute(request, response);
        } catch (IOException | IllegalAccessException | InstantiationException | ServiceException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }
}
