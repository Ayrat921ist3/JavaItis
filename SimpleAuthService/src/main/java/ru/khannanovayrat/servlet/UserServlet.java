package ru.khannanovayrat.servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.khannanovayrat.config.JavaConfiguration;
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
import java.util.logging.Logger;

/**
 * Created by Ayrat on 25.10.2016.
 */
public class UserServlet extends HttpServlet {

    private UserService userService;
    private CarService carService;
    private static Logger log = Logger.getLogger(UserServlet.class.getName());

    @Override
    public void init() throws ServletException {
        super.init();
//        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:context.xml");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfiguration.class);
        userService = (UserService) context.getBean("userService");
        carService = (CarService) context.getBean("carService");
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
        boolean logout = req.getParameter("logout") != null;
        boolean addCar = req.getParameter("addcar") != null;
        String token = (String)req.getAttribute("user_token");
        if(token == null){
            log.info("can't logout: user has not authorized");
        }
        if (logout && token != null) {
            log.info("logging out");
            userService.breakCurrentSession(token);
            req.getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
//            resp.sendRedirect("/login");
        }else if(addCar){
            String model = req.getParameter("model");
            int mileage = Integer.parseInt(req.getParameter("mileage"));
            int owner_id = userService.getUser(token).getId();
            Car car = new Car(mileage, owner_id, model);
            carService.addCar(car);
            log.info("adding car " + car);
        }
    }
}
