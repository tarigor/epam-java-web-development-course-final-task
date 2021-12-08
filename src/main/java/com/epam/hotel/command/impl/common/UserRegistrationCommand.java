package com.epam.hotel.command.impl.common;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.entity.User;
import com.epam.hotel.service.exception.ServiceException;
import com.epam.hotel.types.UserType;
import com.epam.hotel.utility.InputRegex;
import com.epam.hotel.utility.PasswordHandler;
import com.epam.hotel.utility.Validator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Provides the functionality of the new user registration.
 */
public class UserRegistrationCommand extends BaseCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(UserRegistrationCommand.class);
    private static final String REGISTRATION_PAGE = "signup";
    private static final String LOGIN_PAGE = "login";
    private final PasswordHandler passwordHandler = new PasswordHandler();

    /**
     * Handles a GET or POST request received via HTTP from a WEB page.
     *
     * @param request  object that contains the request the client has made of the servlet.
     * @param response object that contains the response the servlet sends to the client.
     * @throws ServerException if the request could not be handled.
     * @throws IOException     when an input or output error is detected when the servlet handles the request.
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException, ServiceException {
        Validator validator = new Validator();

        boolean validateResult = doValidate(request, validator);
        boolean validateDoublePasswordResult = validator.validatePasswordTwice(request.getParameter("password"), request.getParameter("repeatedPassword"));

        if (validateResult && validateDoublePasswordResult) {
//        if (true) {
            User user = buildUserFromPage(request);
            if (commonSiteActivityService.doNewUserRegistration(user)) {
                LOGGER.info("A new user has been added");
                request.setAttribute("registrationCompleted", true);
                doRedirect(request, response, LOGIN_PAGE);
            } else {
                LOGGER.info("There is a such user with such an email");
                request.setAttribute("newUserFault", true);
                doRedirect(request, response, REGISTRATION_PAGE);
            }
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

    private User buildUserFromPage(HttpServletRequest request) throws ServiceException {
        return new User(
                Math.abs(request.getParameter("email").hashCode()),
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                UserType.valueOf(request.getParameter("userType")),
                request.getParameter("email"),
                passwordHandler
                        .setEncryptionKey(Objects.hash(request.getParameter("email")))
                        .encryptPassword(request.getParameter("password")));
    }
}
