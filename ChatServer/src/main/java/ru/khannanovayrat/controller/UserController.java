package ru.khannanovayrat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.khannanovayrat.dao.UserDaoImpl;

/**
 * Created by KFU-user on 24.11.2016.
 */
@RestController
public class UserController {

    @Autowired
    private UserDaoImpl userDao;

    @RequestMapping(value = "users", method = RequestMethod.POST)
    public void createUser(@RequestParam("name") String name){
        userDao.createUser(name);
    }
}
