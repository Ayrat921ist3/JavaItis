package ru.khannanovayrat.service;

import ru.khannanovayrat.dao.UserDao;
import ru.khannanovayrat.models.NewUser;
import ru.khannanovayrat.models.User;

import java.util.List;

/**
 * Created by Ayrat on 25.10.2016.
 */
public class UserService {
    private UserDao userUserDao;

    public UserService(UserDao userUserDao) {
        this.userUserDao = userUserDao;
    }

    public User getUser(int id){
        return userUserDao.getUser(id);
    }

    public User getUser(String username, String password){
        return userUserDao.getUser(username, password);
    }

    public void addUser(NewUser user){
        userUserDao.saveUser(user);
    }

    public void deleteUser(int id){
        userUserDao.deleteUser(id);
    }

    public void updateUser(User user){
        userUserDao.update(user);
    }

    public List<User> getAll(){
        return userUserDao.getAll();
    }
}
