package ru.itis.service;

import ru.itis.dao.UsersDao;
import ru.itis.models.User;

import java.util.List;

/**
 * Created by admin on 06.10.2016.
 */
public class SimpleUsersServiceImpl implements SimpleUsersService {

    private UsersDao usersDao;

    public SimpleUsersServiceImpl() {

    }

    public boolean isRegistered(String userName, String userPassword) {
        if(usersDao != null) {
            List<User> registeredUsers = usersDao.getAll();

            for (User user : registeredUsers) {
                if (user.getName().equals(userName) &&
                        user.getPassword().equals(userPassword)) {
                    return true;
                }
            }
        }else {
            throw new IllegalArgumentException("UsersDao wasn't specified");
        }

        return false;
    }

    public void setUsersDao(UsersDao usersDao){
        this.usersDao = usersDao;
    }
}
