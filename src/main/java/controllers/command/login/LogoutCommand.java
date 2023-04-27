package controllers.command.login;

import Page.ServletPath;
import controllers.command.Command;
import controllers.utils.HttpWrapper;
import controllers.utils.RedirectionManager;
import controllers.utils.SessionManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionManager.getInstance().invalidateSession(request.getSession());
        RedirectionManager.getInstance().redirect(new HttpWrapper(request, response), ServletPath.HOME);
        return RedirectionManager.REDIRECTION;
    }
}
