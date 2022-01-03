package com.epam.hotel.command.impl.admincommand;

import com.epam.hotel.command.BaseCommand;
import com.epam.hotel.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadLogFileCommand extends BaseCommand implements Command {
    private static final String PATH = System.getProperty("user.dir") + "/logs/log.log";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("fileContent", getStringFromLogFile());
        doRedirect(request, response, "logs");
    }

    public String getStringFromLogFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(PATH));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

}
