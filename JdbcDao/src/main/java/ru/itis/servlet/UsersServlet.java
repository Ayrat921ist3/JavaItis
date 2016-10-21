package ru.itis.servlet;

import ru.itis.factory.ServiceFactory;
import ru.itis.models.Owner;
import ru.itis.services.OwnerService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
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
            getServletContext().getRequestDispatcher("/user_form.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String city = req.getParameter("city");
        String age = req.getParameter("age");
        Owner owner = new Owner(name, city, Integer.parseInt(age));
        usersService.addOwner(owner);
        List<Owner> users = usersService.getOwners();
        if (users != null) {
            req.setAttribute("myUsers", users);
            getServletContext().getRequestDispatcher("/user_form.jsp").forward(req, resp);
        }
    }
}
