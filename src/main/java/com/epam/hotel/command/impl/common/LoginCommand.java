package com.epam.hotel.command.impl.common;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.entity.User;
import com.epam.hotel.entity.UserLoginError;
import com.epam.hotel.menu.factory.MenuRole;
import com.epam.hotel.menu.impl.SiteMenuServiceImpl;
import com.epam.hotel.service.exception.ServiceException;
import com.epam.hotel.types.UserType;
import com.epam.hotel.utility.InputRegex;
import com.epam.hotel.utility.PasswordHandler;
import com.epam.hotel.utility.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Provides the functionality of the login action.
 */
public class LoginCommand extends BaseCommand implements Command {
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
        SiteMenuServiceImpl siteMenuService = new SiteMenuServiceImpl();
        Validator validator = new Validator();
        boolean validateResult = validator.validate(
                new String[]{
                        "email",
                        "password"},
                new String[]{
                        request.getParameter("email"),
                        request.getParameter("password")},
                new InputRegex[]{
                        InputRegex.EMAIL,
                        InputRegex.PASSWORD}
        );

        if (validateResult) {
            User userWhileLogin = buildUserLoginCredentials(request);
            User loggedUser = commonSiteActivityService.checkUserForExistingAndRightPasswordInputted(userWhileLogin);
            if (loggedUser != null) {
                if (loggedUser.getUserType().equals(UserType.ADMIN)) {
                    request.getSession().setAttribute("user", loggedUser);
                    request.getSession().setAttribute("menuList",
                            SiteMenuServiceImpl.getInstance().getMenuListCollectedByRoleSortedByID(
                                    MenuRole.COMMON,
                                    MenuRole.ADMIN_LOGGED,
                                    MenuRole.ANYONE_LOGGED));
                    response.sendRedirect("command?name=admin_cabinet");
                } else if (loggedUser.getUserType().equals(UserType.CLIENT)) {
                    request.getSession().setAttribute("user", loggedUser);
                    request.getSession().setAttribute("menuList",
                            SiteMenuServiceImpl.getInstance().getMenuListCollectedByRoleSortedByID(
                                    MenuRole.COMMON,
                                    MenuRole.CLIENT_LOGGED,
                                    MenuRole.ANYONE_LOGGED));
                    boolean loginAndCompleteRequest = Boolean.parseBoolean(request.getParameter("loginAndCompleteRequest"));
                    if (loginAndCompleteRequest) {
                        int persons = Integer.parseInt(request.getParameter("persons"));
                        String roomClass = request.getParameter("roomClass");
                        String dateFrom = request.getParameter("dateFrom");
                        String dateTo = request.getParameter("dateTo");
                        response.sendRedirect(request.getContextPath() + String.format("/command?name=request&persons=%d&roomClass=%s&dateFrom=%s&dateTo=%s", persons, roomClass, dateFrom, dateTo));
                    } else {
                        response.sendRedirect(request.getContextPath() + "/command?name=client_cabinet");
                    }
                }
            } else {
                request.setAttribute("userIsMissing", true);
                request.setAttribute("errorType", UserLoginError.getType());
                doRedirect(request, response, LOGIN_PAGE);
            }
        } else {
            HashMap<String, String> validationResultMap = validator.getMap();
            for (Map.Entry<String, String> entry : validationResultMap.entrySet()) {
                request.setAttribute(entry.getKey() + "State", true);
                request.setAttribute(entry.getKey() + "Desc", entry.getValue());
            }
            doRedirect(request, response, LOGIN_PAGE);
        }
    }

    private User buildUserLoginCredentials(HttpServletRequest request) throws ServiceException {
        return new User(
                0,
                "",
                "",
                UserType.valueOf("CLIENT"),
                request.getParameter("email"),
                passwordHandler
                        .setEncryptionKey(Objects.hash(request.getParameter("email")))
                        .encryptPassword(request.getParameter("password")));
    }
}
