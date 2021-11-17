package com.epam.hotel.command.impl.common;

import com.epam.hotel.command.Command;
import com.epam.hotel.command.CommonSiteActivityCommand;
import com.epam.hotel.entity.User;
import com.epam.hotel.entity.UserType;
import com.epam.hotel.service.CommonSiteActivityService;
import com.epam.hotel.service.factory.ServiceFactory;
import com.epam.hotel.service.factory.ServiceType;
import com.epam.hotel.service.impl.CommonSiteActivityServiceImpl;
import com.epam.hotel.utility.InputRegex;
import com.epam.hotel.utility.PasswordHandler;
import com.epam.hotel.utility.Validator;
import org.apache.log4j.Logger;

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
    private static final Logger logger = Logger.getLogger(UserRegistrationCommand.class);
    private static final String REGISTRATION_PAGE = "signup";
    private static final String LOGIN_PAGE = "login";
    private final PasswordHandler passwordHandler = new PasswordHandler();
    private final CommonSiteActivityService commonSiteActivityService =
            (CommonSiteActivityServiceImpl) ServiceFactory.getInstance().getService(ServiceType.COMMON_SITE_ACTIVITY_SERVICE);

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
                attributesMap.put("registrationCompleted", true);
                request.setAttribute("registrationCompleted", true);
                doRedirect(request, response, LOGIN_PAGE);
            } else {
                logger.info("There is a such user with such an email");
                attributesMap.put("newUserFault", true);
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

    private void doRedirect(HttpServletRequest request, HttpServletResponse response, String pageName) throws IOException {
        try {
            request.getSession().setAttribute("lastpage", pageName);
            request.getRequestDispatcher(String.format("WEB-INF/jsp/%s.jsp", pageName)).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private User buildUserFromPage(HttpServletRequest request) {
        return new User(
                Math.abs(request.getParameter("email").hashCode()),
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                UserType.valueOf(request.getParameter("userType")),
                request.getParameter("email"),
                passwordHandler
                        .setEncryptionKey(Objects.hash(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("email")))
                        .encryptPassword(request.getParameter("password")));
    }
}
