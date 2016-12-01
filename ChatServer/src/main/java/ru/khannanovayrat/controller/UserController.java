package ru.khannanovayrat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import ru.khannanovayrat.dao.UserDaoImpl;

@RestController
public class UserController {

    @Autowired
    private UserDaoImpl userDao;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public void createUser(@RequestParam("name") String name){
        userDao.createUser(name);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String helloUser(@RequestParam("name") String name){
        return "Hello, " + name;
    }
}
