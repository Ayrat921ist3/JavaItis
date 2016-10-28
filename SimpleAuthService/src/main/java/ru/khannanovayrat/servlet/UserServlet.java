package ru.khannanovayrat.servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.khannanovayrat.factory.UserServiceFactory;
import ru.khannanovayrat.service.UserService;
import ru.khannanovayrat.util.Verifier;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:context.xml");
        userService = (UserService) context.getBean("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getCookies()[0].getValue());
        for (Cookie cookie:req.getCookies()){
            if(cookie.getName().equals("token")){
                if (Verifier.verifyTokenValid(cookie.getValue())){
                    System.out.println("NICE " + userService.getAll().size() + " users created");
                    req.setAttribute("myUsers", userService.getAll());
                    getServletContext().getRequestDispatcher("/list.jsp").forward(req, resp);
                }else {
                    System.out.println("token not valid");
                }
            }
        }

    }
}
