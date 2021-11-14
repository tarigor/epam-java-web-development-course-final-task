package com.epam.hotel.command.impl.common;

import com.epam.hotel.command.Command;
import com.epam.hotel.command.CommonSiteActivityCommand;
import com.epam.hotel.entity.User;
import com.epam.hotel.service.CommonSiteActivityService;
import com.epam.hotel.service.factory.ServiceFactory;
import com.epam.hotel.service.factory.ServiceType;
import com.epam.hotel.service.impl.CommonSiteActivityServiceImpl;
import com.epam.hotel.service.impl.PropertiesFileServiceImpl;
import com.epam.hotel.utility.InputRegex;
import com.epam.hotel.utility.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

/**
 * The class provides an implementation of the Login command.
 */
public class LoginCommand extends CommonSiteActivityCommand implements Command {
    private static final String PASSWORD = "userPassword";
    private static final String USER_FIRST_NAME = "userFirstName";
    private static final String USER_FAMILY_NAME = "userFamilyName";
    private static final String LOGIN_PAGE = "WEB-INF/jsp/login.jsp";
    private static final String ADMIN_PAGE = "WEB-INF/jsp/admincabinet.jsp";
    private static final String USER_PAGE = "WEB-INF/jsp/clientcabinet.jsp";
    private final CommonSiteActivityService commonSiteActivityService =
            (CommonSiteActivityServiceImpl) ServiceFactory.getInstance().getService(ServiceType.COMMON_SITE_ACTIVITY_SERVICE);
    private final PropertiesFileServiceImpl propertiesFileService =
            (PropertiesFileServiceImpl) ServiceFactory.getInstance().getService(ServiceType.PROPERTIES_FILE_SERVICE);
    private boolean userFirstNameValidateState;
    private boolean userFamilyNameValidateState;
    private boolean userPasswordValidateState;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        String userFirstName = request.getParameter(USER_FIRST_NAME);
        String userFamilyName = request.getParameter(USER_FAMILY_NAME);
        String password = request.getParameter(PASSWORD);
        if (!inputValidation(userFirstName, userFamilyName, password)) {
            User user = commonSiteActivityService.checkUserForExistingAndRightPasswordInputted(userFirstName, userFamilyName, password);
            if (user != null) {
                request.getSession().setAttribute("userID", user.hashCode());
                if (user.getUserType().contains("admin")) {
                    doRedirect(request, response, ADMIN_PAGE);
                } else {
                    doRedirect(request, response, USER_PAGE);
                }
            } else {
                request.setAttribute("userIsMissing", true);
                request.setAttribute("userMissingMessage", propertiesFileService.getProperties("local.properties").getProperty("user.missing"));
                doRedirect(request, response, LOGIN_PAGE);
            }
        } else {
            System.out.println("is here");
            request.setAttribute("userFirstNameValidateState", userFirstNameValidateState);
            request.setAttribute("userFirstNameValidateStateDesc", InputRegex.getDescription(InputRegex.NAME));
            request.setAttribute("userFamilyNameValidateState", userFamilyNameValidateState);
            request.setAttribute("userFamilyNameValidateStateDesc", InputRegex.getDescription(InputRegex.NAME));
            request.setAttribute("userPasswordValidateState ", userPasswordValidateState);
            request.setAttribute("userPasswordValidateStateDesc", InputRegex.getDescription(InputRegex.PASSWORD));
            doRedirect(request, response, LOGIN_PAGE);
        }

    }

    private void doRedirect(HttpServletRequest request, HttpServletResponse response, String page) throws IOException {
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private boolean inputValidation(String userFirstName, String userFamilyName, String userPassword) {
        userFirstNameValidateState = Validator.checkInput(InputRegex.NAME, userFirstName);
        userFamilyNameValidateState = Validator.checkInput(InputRegex.NAME, userFamilyName);
        userPasswordValidateState = Validator.checkInput(InputRegex.PASSWORD, userPassword);
        return userFirstNameValidateState || userFamilyNameValidateState || false;
    }
}
