package com.epam.hotel.command.impl.common;

import com.epam.hotel.command.Command;
import com.epam.hotel.command.CommonSiteActivityCommand;
import com.epam.hotel.entity.User;
import com.epam.hotel.entity.UserType;
import com.epam.hotel.utility.InputRegex;
import com.epam.hotel.utility.PasswordHandler;
import com.epam.hotel.utility.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The class provides an implementation of the new user registration command.
 */
public class UserRegistrationCommand extends CommonSiteActivityCommand implements Command {
    private static final String REGISTRATION_PAGE = "WEB-INF/jsp/signup.jsp";
    private final PasswordHandler passwordHandler = new PasswordHandler();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        Validator validator = new Validator();

        boolean validateResult = doValidate(request, validator);
        boolean validateDoublePasswordResult = validator.validateDoublePassword(request.getParameter("password"), request.getParameter("repeatedPassword"));

        if (validateResult && validateDoublePasswordResult) {
            User user = buildUserFromPage(request);
        } else {
            HashMap<String, String> validationResultMap = validator.getMap();
            for (Map.Entry<String, String> entry : validationResultMap.entrySet()) {
                request.setAttribute(entry.getKey() + "State", true);
                request.setAttribute(entry.getKey() + "Desc", entry.getValue());
            }
            request.setAttribute("passwordDoubleCheckState", !validateDoublePasswordResult);
            request.setAttribute("passwordDoubleCheckDesc", InputRegex.getDescription(InputRegex.PASSWORD_DOUBLE_CHECK));
            doRedirect(request, response, REGISTRATION_PAGE);
        }
    }

    private boolean doValidate(HttpServletRequest request, Validator validator) {
        return validator.validate(
                new String[]{
                        "firstName",
                        "lastName",
                        "email",
                        "password"},
                new String[]{
                        request.getParameter("firstName"),
                        request.getParameter("lastName"),
                        request.getParameter("email"),
                        request.getParameter("password")},
                new InputRegex[]{
                        InputRegex.NAME,
                        InputRegex.NAME,
                        InputRegex.EMAIL,
                        InputRegex.PASSWORD}
        );
    }

    private void doRedirect(HttpServletRequest request, HttpServletResponse response, String page) throws IOException {
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private User buildUserFromPage(HttpServletRequest request) {
        return new User(
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                UserType.valueOf(request.getParameter("userType")),
                request.getParameter("email"),
                passwordHandler
                        .setEncryptionKey(Objects.hash(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("email")))
                        .encryptPassword(request.getParameter("password")));
    }
}
