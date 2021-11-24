package com.epam.hotel.command.impl.common;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.entity.User;
import com.epam.hotel.entity.UserType;
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
 * The class provides an implementation of the new user registration command.
 */
public class UserRegistrationCommand extends BaseCommand implements Command {
    private static final Logger logger = Logger.getLogger(UserRegistrationCommand.class);
    private static final String REGISTRATION_PAGE = "signup";
    private static final String LOGIN_PAGE = "login";
    private final PasswordHandler passwordHandler = new PasswordHandler();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        Validator validator = new Validator();

        boolean validateResult = doValidate(request, validator);
        boolean validateDoublePasswordResult = validator.validateDoublePassword(request.getParameter("password"), request.getParameter("repeatedPassword"));

//        if (validateResult && validateDoublePasswordResult) {
        if (true) {
            User user = buildUserFromPage(request);
            if (commonSiteActivityService.doNewUserRegistration(user)) {
                logger.info("A new user has been added");
                request.setAttribute("registrationCompleted", true);
                doRedirect(request, response, LOGIN_PAGE);
            } else {
                logger.info("There is a such user with such an email");
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

    private User buildUserFromPage(HttpServletRequest request) {
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
