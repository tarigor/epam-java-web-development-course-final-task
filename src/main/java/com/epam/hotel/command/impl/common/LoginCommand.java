package com.epam.hotel.command.impl.common;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.entity.User;
import com.epam.hotel.entity.UserLoginError;
import com.epam.hotel.entity.UserType;
import com.epam.hotel.menu.factory.MenuRole;
import com.epam.hotel.menu.impl.SiteMenuServiceImpl;
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
 * The class provides an implementation of the Login command.
 */
public class LoginCommand extends BaseCommand implements Command {
    private static final String LOGIN_PAGE = "login";
    private static final String ADMIN_PAGE = "admincabinet";
    private static final String USER_PAGE = "clientcabinet";
    private static final String ROOMS_LIST_PAGE = "roomslist";
    private final PasswordHandler passwordHandler = new PasswordHandler();
    private SiteMenuServiceImpl siteMenuService = new SiteMenuServiceImpl();

    /**
     * @param request
     * @param response
     * @throws ServerException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {

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

//        if (validateResult) {
        if (true) {
            User userWhileLogin = buildUserLoginCredentials(request);
            User loggedUser = commonSiteActivityService.checkUserForExistingAndRightPasswordInputted(userWhileLogin);
            if (loggedUser != null) {
                if (loggedUser.getUserType().equals(UserType.ADMIN)) {
                    request.getSession().setAttribute("user", loggedUser);
                    request.getSession().setAttribute("menuList", siteMenuService.getMenuListCollectedByRoleSortedByID(MenuRole.COMMON, MenuRole.ADMIN_LOGGED, MenuRole.ANYONE_LOGGED));
                    response.sendRedirect("command?name=admin_cabinet");
                } else if (loggedUser.getUserType().equals(UserType.CLIENT)) {
                    request.getSession().setAttribute("user", loggedUser);
                    request.getSession().setAttribute("menuList", siteMenuService.getMenuListCollectedByRoleSortedByID(MenuRole.COMMON, MenuRole.CLIENT_LOGGED, MenuRole.ANYONE_LOGGED));
                    boolean loginAndCompleteRequest = Boolean.parseBoolean(request.getParameter("loginAndCompleteRequest"));
                    if (loginAndCompleteRequest) {
                        int persons = Integer.parseInt(request.getParameter("persons"));
                        String roomClass = request.getParameter("roomClass");
                        String dateFrom = request.getParameter("dateFrom");
                        String dateTo = request.getParameter("dateTo");
                        response.sendRedirect(String.format("command?name=request&persons=%d&roomClass=%s&dateFrom=%s&dateTo=%s", persons, roomClass, dateFrom, dateTo));
                    } else {
                        response.sendRedirect("command?name=client_cabinet");
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

    private User buildUserLoginCredentials(HttpServletRequest request) {
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
