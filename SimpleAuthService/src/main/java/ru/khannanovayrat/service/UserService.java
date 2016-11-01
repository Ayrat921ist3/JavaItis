package ru.khannanovayrat.service;

import ru.khannanovayrat.dao.UserDao;
import ru.khannanovayrat.models.NewUser;
import ru.khannanovayrat.models.User;
import ru.khannanovayrat.util.Password;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Ayrat on 25.10.2016.
 */
public class UserService {
    private UserDao userUserDao;
    private static Logger log = Logger.getLogger(UserService.class.getName());

    public UserService(UserDao userUserDao) {
        this.userUserDao = userUserDao;
    }

    public User getUser(int id){
        log.info("getting user, id = " + id);
        return userUserDao.getUser(id);
    }

    public User getUser(String username, String password){
        log.info("getting user, username = " + username +
                " password = " + Password.hash(password));
        return userUserDao.getUser(username, password);
    }

    public void addUser(NewUser user){
        log.info("saving user, " + user);
        userUserDao.saveUser(user);
    }

    public void deleteUser(int id){
        log.info("deleting user, id = " + id);
        userUserDao.deleteUser(id);
    }

    public void updateUser(User user){
        log.info("updating user, " + user);
        userUserDao.update(user);
    }

    public List<User> getAll(){
        log.info("getting all users.. ");
        return userUserDao.getAll();
    }

    public User getUser(String token) {
        log.info("getting user, token = " + token);
        return userUserDao.getUser(token);
    }

    public void breakCurrentSession(String token) {
        log.info("deleting session with token " + token);
        userUserDao.deleteToken(token);
    }
}
