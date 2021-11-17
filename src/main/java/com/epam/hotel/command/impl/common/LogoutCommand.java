package com.epam.hotel.command.impl.common;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.menu.factory.MenuRole;
import com.epam.hotel.menu.impl.SiteMenuServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

/**
 * The class provides an implementation of the Logout command.
 */
public class LogoutCommand extends BaseCommand implements Command {
    private static final String MAIN_PAGE = "index";
    private SiteMenuServiceImpl siteMenuService = new SiteMenuServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        request.getSession().invalidate();
        request.getSession().setAttribute("menuList", siteMenuService.getMenuListCollectedByRoleSortedByID(MenuRole.COMMON, MenuRole.ANYONE_NOT_LOGGED));
        doRedirect(request, response, MAIN_PAGE);
    }
}
