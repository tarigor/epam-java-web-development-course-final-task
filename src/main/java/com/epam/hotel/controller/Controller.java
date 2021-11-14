package com.epam.hotel.controller;

import com.epam.hotel.command.Command;
import com.epam.hotel.command.factory.CommandFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Controller.class);
    public static final String COMMAND_NAME = "name";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doHandling(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doHandling(req, resp);
    }

    private void doHandling(HttpServletRequest req, HttpServletResponse resp) {
//        String command = req.getParameter(COMMAND).toUpperCase();
        System.out.println("i'm in controller");
        String command = (String) req.getAttribute(COMMAND_NAME);
        logger.info(String.format("the following command detected - /%s", command));
        Class commandClass = CommandFactory.getInstance().getCommand(command);
        try {
            ((Command) commandClass.newInstance()).execute(req, resp);
        } catch (IOException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

}
