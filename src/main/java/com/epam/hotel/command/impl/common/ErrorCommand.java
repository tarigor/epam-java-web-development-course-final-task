package com.epam.hotel.command.impl.common;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;
import com.epam.hotel.types.ErrorStatus;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorCommand extends BaseCommand implements Command {
    public static final String ERROR_PREFIX = "ERROR_";
    private static final Logger LOGGER = Logger.getLogger(ErrorCommand.class);
    private static final String ERROR_PAGE = "error";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        LOGGER.error(String.format("ERROR STATUS CODE DETECTED - %d", statusCode));
        request.setAttribute("errorMessage", ErrorStatus.valueOf(ERROR_PREFIX + statusCode).getDescription());
        doRedirect(request, response, ERROR_PAGE);
    }
}
