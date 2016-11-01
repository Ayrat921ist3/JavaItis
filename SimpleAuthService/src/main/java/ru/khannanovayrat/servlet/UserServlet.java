package ru.khannanovayrat.servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.khannanovayrat.factory.UserServiceFactory;
import ru.khannanovayrat.models.Car;
import ru.khannanovayrat.models.User;
import ru.khannanovayrat.service.CarService;
import ru.khannanovayrat.service.UserService;
import ru.khannanovayrat.util.CookieHelper;
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
    private CarService carService;

    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:context.xml");
        userService = (UserService) context.getBean("userService");
        carService = (CarService) context.getBean("carsService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = (String)req.getAttribute("user_token");
        User currentUser = userService.getUser(token);
        req.setAttribute("myCars", carService.getAll(currentUser.getId()));
        getServletContext().getRequestDispatcher("/list.jsp").forward(req, resp);
//        for (Cookie cookie:req.getCookies()){
//            if(cookie.getName().equals("token")){
//                if (Verifier.verifyTokenValid(cookie.getValue())){
//                    User currentUser = userService.getUser(cookie.getValue());
//                    req.setAttribute("myCars", carService.getAll(currentUser.getId()));
//                    getServletContext().getRequestDispatcher("/list.jsp").forward(req, resp);
//                }else {
//                    System.out.println("token not valid");
//                }
//            }
//        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String model = req.getParameter("model");
        int mileage = Integer.parseInt(req.getParameter("mileage"));
        CookieHelper cookieHelper = new CookieHelper(req);
        int owner_id = userService.getUser(cookieHelper.getCookie("token")
                .getValue()).getId();
        carService.addCar(new Car(mileage, owner_id, model));
        resp.sendRedirect("/list");
    }
}
