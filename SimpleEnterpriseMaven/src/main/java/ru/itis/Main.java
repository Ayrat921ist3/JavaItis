package ru.itis;

import ru.itis.dao.UsersDao;
import ru.itis.dao.UsersDaoFileBasedImpl;
import ru.itis.models.User;
import ru.itis.service.SimpleUsersService;
import ru.itis.service.SimpleUsersServiceImpl;

public class Main {

    public static void main(String[] args) {
        UsersDao usersDao = new UsersDaoFileBasedImpl("C:\\Users\\Ayrat\\Desktop\\JavaItis\\SimpleEnterpriseMaven\\",
                "UsersOriginal.txt", "Users.txt");

        SimpleUsersService service = new SimpleUsersServiceImpl(usersDao);

        System.out.println(service.isRegistered("Marsel", "qwerty008"));

        //unique user
        User user = new User(4, "Vasya", "qazwsx", 23);

        //existing user
        User user1 = new User(1, "Marsel", "qwerty007", 21);
        usersDao.save(user);
        usersDao.save(user1);

        usersDao.delete(2);
    }
}
