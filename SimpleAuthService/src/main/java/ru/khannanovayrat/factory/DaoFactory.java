package ru.khannanovayrat.factory;

import ru.khannanovayrat.dao.UserDao;
import ru.khannanovayrat.dao.UserJdbcUserDaoImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created by Ayrat on 25.10.2016.
 */
public class DaoFactory {
    private static DaoFactory instance;
    private UserDao userDao;

    static{
        instance = new DaoFactory();
    }

    private DaoFactory(){
        Properties properties = new Properties();
        try {
//            properties.load(new FileInputStream("C:\\Users\\Ayrat\\Desktop\\" +
//                    "JavaItis\\SimpleAuthService\\src\\main\\resources\\userDao.properties"));
            properties.load(new FileInputStream("C:\\Users\\KFU-user\\Desktop\\JavaItis" +
                    "\\SimpleAuthService\\src\\main\\resources\\userDao.properties"));
            String userDaoClassName = properties.getProperty("dao.className");
            Class clazz = Class.forName(userDaoClassName);
            Constructor constructor = clazz.getConstructor(Connection.class);
            Connection connection = ConnectionFactory.getInstance().getConnection();
            userDao = (UserJdbcUserDaoImpl)constructor.newInstance(connection);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static DaoFactory getInstance(){
        return instance;
    }

    public UserJdbcUserDaoImpl getUserDao(){
        return (UserJdbcUserDaoImpl) userDao;
    }
}
