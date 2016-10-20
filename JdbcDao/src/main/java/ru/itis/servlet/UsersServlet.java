package ru.itis.servlet;

import ru.itis.factory.ServiceFactory;
import ru.itis.models.Owner;
import ru.itis.services.OwnerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by KFU-user on 20.10.2016.
 */
public class UsersServlet extends HttpServlet {

    private OwnerService usersService;

    @Override
    public void init() throws ServletException {
        super.init();
        usersService = ServiceFactory.getInstance().getOwnerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Owner> users = usersService.getOwners();
        if (users != null) {
            req.setAttribute("myUsers", users);
            getServletContext().getRequestDispatcher("/user.jsp").forward(req, resp);
        }

    }
}
