package ru.itis.factory;

import ru.itis.dao.Dao;
import ru.itis.dao.OwnersDaoJdbcImpl;
import ru.itis.services.CarService;
import ru.itis.services.CarServiceImpl;
import ru.itis.services.OwnerService;
import ru.itis.services.OwnerServiceImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * Created by KFU-user on 13.10.2016.
 */
public class ServiceFactory {
    private static ServiceFactory instance;
    private Properties properties;
    private OwnerService ownerService;
    private CarService carService;

    static {
        instance = new ServiceFactory();
    }

    private ServiceFactory() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("C:\\Users\\KFU-user\\Desktop\\JavaItis\\JdbcDao\\src\\main\\resources\\service.properties"));
//            properties.load(new FileInputStream("C:\\Users\\Ayrat\\Desktop\\JavaItis\\JdbcDao\\src\\main\\resources\\service.properties"));
            String ownerServiceClassName = properties.getProperty("service.owner_class");
            Class clazz = Class.forName(ownerServiceClassName);

            Constructor constructor = clazz.getConstructor(Dao.class);
            ownerService = (OwnerService) constructor.newInstance(DaoJdbcImplFactory.getInstance().getOwnersDao());

            String carSerciceClassName = properties.getProperty("service.car_class");
            clazz = Class.forName(carSerciceClassName);
            constructor = clazz.getConstructor(Dao.class);
            carService = (CarService) constructor.newInstance(DaoJdbcImplFactory.getInstance().getCarsDao());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (ClassNotFoundException e){
            throw new IllegalArgumentException(e);
        } catch (NoSuchMethodException e){
            throw new IllegalArgumentException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public static ServiceFactory getInstance(){
        return instance;
    }

    public OwnerService getOwnerService(){
        return ownerService;
    }

    public CarService getCarService(){
        return carService;
    }
}
