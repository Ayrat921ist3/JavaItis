package ru.khannanovayrat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.khannanovayrat.dao.UserDao;
import ru.khannanovayrat.dto.UserDto;

/**
 * Created by KFU-user on 24.11.2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void createUser(UserDto newUser) {
        userDao.createUser(newUser);
    }
}
