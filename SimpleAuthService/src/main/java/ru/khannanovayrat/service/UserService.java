package ru.khannanovayrat.service;

import ru.khannanovayrat.dao.Dao;
import ru.khannanovayrat.models.NewUser;
import ru.khannanovayrat.models.User;

/**
 * Created by Ayrat on 25.10.2016.
 */
public class UserService {
    private Dao userDao;

    public UserService(Dao userDao) {
        this.userDao = userDao;
    }

    public User getUser(int id){
        return userDao.getUser(id);
    }

    public User getUser(String username, String password){
        return userDao.getUser(username, password);
    }

    public void addUser(NewUser user){
        userDao.saveUser(user);
    }

    public void deleteUser(int id){
        userDao.deleteUser(id);
    }

    public void updateUser(User user){
        userDao.update(user);
    }
}
