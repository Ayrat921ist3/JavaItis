package ru.khannanovayrat.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.khannanovayrat.config.JavaConfiguration;
import ru.khannanovayrat.models.User;
import ru.khannanovayrat.service.UserService;
import ru.khannanovayrat.util.Token;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

import static ru.khannanovayrat.util.Verifier.verifyUserExists;

/**
 * Created by KFU-user on 09.11.2016.
 */
@Controller
public class LoginController {

    private Logger log = Logger.getLogger(LoginController.class.getName());
    @Autowired
    private UserService userService;

    public LoginController() {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfiguration.class);
//        userService = context.getBean(UserService.class);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLogin() throws Exception {
        log.info("get method");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView postLogin(HttpServletResponse httpServletResponse, @RequestParam("username") String username,
                                  @RequestParam("password") String password,
                                  @RequestParam(value = "signup", required = false) String sign){
        log.info("post method");

        ModelAndView modelAndView = new ModelAndView();

        if (sign != null) {
            modelAndView.setViewName("registration");
            return modelAndView;
        }
        if(verifyUserExists(username, password)){
            User user = userService.getUser(username, password);
            if(user.getToken() == null || user.getToken().equals("")) {
                String token = Token.nextSessionId();
                Cookie cookie = new Cookie("token", token);
                user.setToken(token);
                userService.updateUser(user);
                httpServletResponse.addCookie(cookie);
            }
            modelAndView.setViewName("list");
        }
        return modelAndView;
    }
}
