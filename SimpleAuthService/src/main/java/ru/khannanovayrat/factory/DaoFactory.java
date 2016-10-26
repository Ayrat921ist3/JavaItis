package ru.khannanovayrat.factory;

import ru.khannanovayrat.dao.Dao;
import ru.khannanovayrat.dao.UserJdbcDaoImpl;

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
    private Dao dao;

    static{
        instance = new DaoFactory();
    }

    private DaoFactory(){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("C:\\Users\\Ayrat\\Desktop\\" +
                    "JavaItis\\SimpleAuthService\\src\\main\\resources\\dao.properties"));
            String userDaoClassName = properties.getProperty("dao.className");
            Class clazz = Class.forName(userDaoClassName);
            Constructor constructor = clazz.getConstructor(Connection.class);
            Connection connection = ConnectionFactory.getInstance().getConnection();
            dao = (UserJdbcDaoImpl)constructor.newInstance(connection);
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

    public UserJdbcDaoImpl getDao(){
        return (UserJdbcDaoImpl) dao;
    }
}
