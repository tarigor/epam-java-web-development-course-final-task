package com.epam.hotel.controller;

import com.epam.hotel.command.Command;
import com.epam.hotel.command.factory.CommandFactory;
import com.epam.hotel.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    public static final String COMMAND_NAME = "name";
    private static final Logger logger = Logger.getLogger(Controller.class);

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
        String command = (String) req.getAttribute(COMMAND_NAME);
        logger.info(String.format("the following command detected - /%s", command));

        if (checkCommandRole(req, command)) {

            Class commandClass = CommandFactory.getInstance().getCommand(command);
            try {
                ((Command) commandClass.newInstance()).execute(req, resp);
            } catch (IOException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("you are not authorized to do this");
            req.setAttribute("errorMessage", "not.authorized.access");
            try {
                req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkCommandRole(HttpServletRequest req, String command) {
        String commandRole = CommandFactory.getInstance().getCommandRole(command);
        System.out.println("command role -> " + commandRole);
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            System.out.println("user role -> " + user.getUserType().name());
        }
        if (commandRole.equals("ANYONE")) {
            return true;
        }
        assert user != null;
        return commandRole.contains(user.getUserType().name());
    }

}
