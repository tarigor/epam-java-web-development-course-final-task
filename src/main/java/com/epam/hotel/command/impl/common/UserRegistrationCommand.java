package com.epam.hotel.command.impl.common;

import com.epam.hotel.command.Command;
import com.epam.hotel.command.CommonSiteActivityCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

public class UserRegistrationCommand extends CommonSiteActivityCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {

    }
}
