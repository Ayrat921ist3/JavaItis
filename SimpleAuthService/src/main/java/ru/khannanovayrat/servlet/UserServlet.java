package ru.khannanovayrat.servlet;

import ru.khannanovayrat.factory.UserServiceFactory;
import ru.khannanovayrat.service.UserService;
import ru.khannanovayrat.util.Verifier;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ayrat on 25.10.2016.
 */
public class UserServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = UserServiceFactory.getInstance().getUserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getCookies()[0].getValue());
        if (Verifier.verifyTokenValid(req.getCookies()[0].getValue())){
            System.out.println("NICE");
        }
        getServletContext().getRequestDispatcher("/list.jsp").forward(req, resp);
    }
}
