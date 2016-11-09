package ru.khannanovayrat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.khannanovayrat.models.NewUser;
import ru.khannanovayrat.service.UserService;
import ru.khannanovayrat.util.Password;
import ru.khannanovayrat.util.Verifier;

import java.util.logging.Logger;

/**
 * Created by Ayrat on 09.11.2016.
 */
@Controller
public class RegistrationController {

    private Logger log = Logger.getLogger(RegistrationController.class.getName());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView getRegistration(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView postRegistration(@RequestParam("username") String username,
                                         @RequestParam("password") String password,
                                         @RequestParam("fio") String fio){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        Verifier verifier = new Verifier();
        if(!verifier.verifyUsernameExist(username)) {
            NewUser newUser = new NewUser(fio, password, username);
            userService.addUser(newUser);
        }else{
            System.out.print("user exists");
        }
        return modelAndView;
    }
}
