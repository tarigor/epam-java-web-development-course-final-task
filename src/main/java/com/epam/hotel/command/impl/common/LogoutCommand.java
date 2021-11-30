package com.epam.hotel.command.impl.common;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.menu.factory.MenuRole;
import com.epam.hotel.menu.impl.SiteMenuServiceImpl;
import com.epam.hotel.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

/**
 * Provides the functionality of the logout action.
 */
public class LogoutCommand extends BaseCommand implements Command {
    private static final String MAIN_PAGE = "index";

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
        request.getSession().invalidate();
        request.getSession().setAttribute("menuList", SiteMenuServiceImpl.getInstance().getMenuListCollectedByRoleSortedByID(MenuRole.COMMON, MenuRole.ANYONE_NOT_LOGGED));
        doRedirect(request, response, MAIN_PAGE);
    }
}
