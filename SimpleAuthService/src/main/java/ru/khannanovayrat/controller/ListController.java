package ru.khannanovayrat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.khannanovayrat.models.Car;
import ru.khannanovayrat.models.User;
import ru.khannanovayrat.service.CarService;
import ru.khannanovayrat.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

/**
 * Created by KFU-user on 09.11.2016.
 */
@Controller
public class ListController {

    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;

    private static Logger log = Logger.getLogger(ListController.class.getName());

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView getList(HttpServletRequest req){
        String token = (String)req.getAttribute("user_token");
        User currentUser = userService.getUser(token);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("list");
        modelAndView.addObject("myCars", carService.getAll(currentUser.getId()));
        return modelAndView;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ModelAndView postList(HttpServletRequest req, @RequestParam(value = "logout", required = false) String logoutPar,
                                 @RequestParam(value = "addcar", required = false) String addCarPar){
        boolean logout = logoutPar != null;
        boolean addCar = addCarPar != null;
        String token = (String)req.getAttribute("user_token");
        ModelAndView modelAndView = new ModelAndView();
        if(token == null){
            log.info("can't logout: user has not authorized");
        }
        if (logout && token != null) {
            log.info("logging out");
            userService.breakCurrentSession(token);
            modelAndView.setViewName("login");
//            resp.sendRedirect("/login");
        }else if(addCar){
            String model = req.getParameter("model");
            int mileage = Integer.parseInt(req.getParameter("mileage"));
            int owner_id = userService.getUser(token).getId();
            Car car = new Car(mileage, owner_id, model);
            carService.addCar(car);
            log.info("adding car " + car);
            User currentUser = userService.getUser(token);
            modelAndView.addObject("myCars", carService.getAll(currentUser.getId()));
        }
        return modelAndView;
    }
}
