package ru.khannanovayrat.servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.khannanovayrat.config.JavaConfiguration;
import ru.khannanovayrat.factory.UserServiceFactory;
import ru.khannanovayrat.models.NewUser;
import ru.khannanovayrat.service.UserService;
import ru.khannanovayrat.util.Password;
import ru.khannanovayrat.util.Verifier;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ayrat on 26.10.2016.
 */
public class RegistrationServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
//        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:context.xml");
//        userService = (UserService) context.getBean("userService");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfiguration.class);
        userService = (UserService)context.getBean("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = Password.hash(req.getParameter("password"));
        String fio = req.getParameter("fio");
        String sign = req.getParameter("signin");
        if(sign != null){
            resp.sendRedirect("/login");
            return;
        }
        if(!Verifier.verifyUsernameExist(username)) {
            NewUser newUser = new NewUser(fio, password, username);
            userService.addUser(newUser);

            resp.sendRedirect(req.getContextPath() + "/login");
        }else{
            System.out.print("user exists");
        }
    }
}
