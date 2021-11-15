package com.epam.hotel.command.impl.common;

import com.epam.hotel.command.Command;
import com.epam.hotel.command.CommonSiteActivityCommand;
import com.epam.hotel.entity.User;
import com.epam.hotel.entity.UserType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

/**
 * The class provides an implementation of the new user registration command.
 */
public class UserRegistrationCommand extends CommonSiteActivityCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        User user = buildUserFromPage(request);
        System.out.println("user->" + user);
    }

    private User buildUserFromPage(HttpServletRequest request) {
        return new User(
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                UserType.valueOf(request.getParameter("userType")),
                request.getParameter("email"),
                request.getParameter("password")
        );
    }
}
