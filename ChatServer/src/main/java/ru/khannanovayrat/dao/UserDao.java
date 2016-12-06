package ru.khannanovayrat.dao;

import ru.khannanovayrat.dto.UserDto;

/**
 * Created by KFU-user on 24.11.2016.
 */
public interface UserDao {

    void createUser(UserDto user);
}
