package ru.khannanovayrat.dao;

import ru.khannanovayrat.models.Car;
import ru.khannanovayrat.models.NewUser;
import ru.khannanovayrat.models.User;

import java.util.List;

/**
 * Created by Ayrat on 25.10.2016.
 */
public interface UserDao {
    User getUser(int id);
    void saveUser(NewUser user);
    void deleteUser(int id);
    void addUserCar(Car car);
    void update(User user);
    List<User> getAll();

    User getUser(String name, String password);
}
