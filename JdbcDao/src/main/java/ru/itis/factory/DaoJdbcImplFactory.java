package ru.itis.factory;

import ru.itis.dao.CarsDaoJdbcImpl;
import ru.itis.dao.OwnersDaoJdbcImpl;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created by KFU-user on 13.10.2016.
 */
public class DaoJdbcImplFactory {

    private static DaoJdbcImplFactory instance;
    private Properties properties;
    private OwnersDaoJdbcImpl ownersDao;
    private CarsDaoJdbcImpl carsDao;


    static {
        instance = new DaoJdbcImplFactory();
    }

    private DaoJdbcImplFactory(){
        try{
            properties = new Properties();
            properties.load(new FileInputStream("C:\\Users\\KFU-user\\Desktop\\JavaItis\\JdbcDao\\src\\main\\resources\\dao.properties"));
//            properties.load(new FileInputStream("C:\\Users\\Ayrat\\Desktop\\JavaItis\\JdbcDao\\src\\main\\resources\\dao.properties"));
            String ownerDaoClass = properties.getProperty("dao.owners_class");
            Class clazz = Class.forName(ownerDaoClass);
            Constructor constructor = clazz.getConstructor(Connection.class);
            ownersDao = (OwnersDaoJdbcImpl) constructor.newInstance(JdbcConnectionFactory.getInstance().getConnection());

            String carDaoClass = properties.getProperty("dao.cars_class");
            clazz = Class.forName(carDaoClass);
            constructor = clazz.getConstructor(Connection.class);
            carsDao = (CarsDaoJdbcImpl) constructor.newInstance(JdbcConnectionFactory.getInstance().getConnection());
        }catch (Exception e){
            throw new IllegalArgumentException(e);
        }
    }

    public static DaoJdbcImplFactory getInstance(){
        return instance;
    }

    public OwnersDaoJdbcImpl getOwnersDao(){
        return ownersDao;
    }

    public CarsDaoJdbcImpl getCarsDao(){
        return carsDao;
    }
}
