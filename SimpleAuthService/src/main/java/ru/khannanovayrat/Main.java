package ru.khannanovayrat;


import ru.khannanovayrat.factory.UserServiceFactory;
import ru.khannanovayrat.models.NewUser;
import ru.khannanovayrat.models.User;
import ru.khannanovayrat.service.UserService;


/**
 * Created by Ayrat on 25.10.2016.
 */
public class Main {

    public static void main(String[] args) {
        UserService userService = UserServiceFactory.getInstance().getUserService();
        userService.addUser(new NewUser("Vasiliy", "vasya123", "vasyok"));
        System.out.println(userService.getUser(1));

        //updating user
        User user = new User(1, "Vasiliy Ivanov", "vasya123", null, "vasyok");
        userService.updateUser(user);
        System.out.println(userService.getUser(1));
    }
}
