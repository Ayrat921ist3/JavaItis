package ru.khannanovayrat.factory;

import ru.khannanovayrat.dao.Dao;
import ru.khannanovayrat.service.UserService;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * Created by Ayrat on 25.10.2016.
 */
public class UserServiceFactory {

    private static UserServiceFactory instance;
    private UserService userService;

    static {
        instance = new UserServiceFactory();
    }

    private UserServiceFactory() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("C:\\Users\\Ayrat\\Desktop\\" +
                    "JavaItis\\SimpleAuthService\\src\\main\\resources\\service.properties"));
            String className = properties.getProperty("service.className");
            Class clazz = Class.forName(className);
            Constructor constructor = clazz.getConstructor(Dao.class);
            userService = (UserService) constructor.newInstance(DaoFactory.getInstance().getDao());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        } catch (InstantiationException e) {
            throw new IllegalArgumentException(e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static UserServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService(){
        return userService;
    }
}
