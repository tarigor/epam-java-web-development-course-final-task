package com.epam.hotel.command.impl.common;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

public class BookCommand extends BaseCommand implements Command {
    private static final String LOGIN_PAGE = "login";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        String[] singleRoomsSelected = request.getParameterValues("singleRoomsSelected");
        String[] doubleRoomsSelected = request.getParameterValues("doubleRoomsSelected");
        String[] suiteRoomsSelected = request.getParameterValues("suiteRoomsSelected");
        String[] deluxeRoomsSelected = request.getParameterValues("deluxeRoomsSelected");
        String dateFrom = request.getParameter("dateFrom");
        String dateTo = request.getParameter("dateTo");

        System.out.println("dateFrom->" + dateFrom + " " + "dateTo->" + dateTo);

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            request.setAttribute("singleRoomsSelected", singleRoomsSelected);
            request.setAttribute("doubleRoomsSelected", doubleRoomsSelected);
            request.setAttribute("suiteRoomsSelected", suiteRoomsSelected);
            request.setAttribute("deluxeRoomsSelected", deluxeRoomsSelected);
            request.setAttribute("dateFrom", dateFrom);
            request.setAttribute("dateTo", dateTo);
            request.setAttribute("loginAndCompleteBooking", true);
            doRedirect(request, response, LOGIN_PAGE);
        } else {
            System.out.println("user->" + user.toString());
        }

        for (String s : singleRoomsSelected) {
            System.out.println("->" + s);
        }
    }
}