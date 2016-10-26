package ru.khannanovayrat.servlet;

import ru.khannanovayrat.factory.UserServiceFactory;
import ru.khannanovayrat.models.User;
import ru.khannanovayrat.service.UserService;
import ru.khannanovayrat.util.Token;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.khannanovayrat.util.Verifier.verifyUserExists;

/**
 * Created by Ayrat on 26.10.2016.
 */
public class LoginServlet extends HttpServlet{

    UserService service;

    @Override
    public void init() throws ServletException {
        super.init();
        service = UserServiceFactory.getInstance().getUserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(verifyUserExists(username, password)){
            User user = service.getUser(username, password);
            if(user.getToken() == null || user.getToken().equals("")) {
                String token = Token.nextSessionId();
                Cookie cookie = new Cookie("token", token);
                user.setToken(token);
                service.updateUser(user);
                resp.addCookie(cookie);
            }
            resp.sendRedirect(req.getContextPath() + "/list");
        }
    }
}
