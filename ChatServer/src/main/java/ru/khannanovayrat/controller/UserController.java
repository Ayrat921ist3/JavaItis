package ru.khannanovayrat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import ru.khannanovayrat.dao.UserDaoImpl;
import ru.khannanovayrat.dto.UserDto;
import ru.khannanovayrat.service.UserService;
import ru.khannanovayrat.util.Password;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
public class UserController {

    @Autowired
    private UserDaoImpl userDao;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public void createUser(@RequestBody UserDto newUser){
        userService.createUser(newUser);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String helloUser(@RequestParam("name") String password){

        boolean passExists = false;
        String anotherPass = "password1";
        try {
            String hashedPass = Password.getSecureSHA1Password(password);
            passExists = Password.validatePassword(anotherPass, hashedPass);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return String.valueOf(passExists);
    }
}
