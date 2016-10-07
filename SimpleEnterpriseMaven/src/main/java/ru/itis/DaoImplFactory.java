package ru.itis;

import ru.itis.dao.UsersDao;
import ru.itis.service.SimpleUsersService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class DaoImplFactory {

    private static DaoImplFactory instance;
    private UsersDao usersDao;
    private Properties daoProperties;
    private SimpleUsersService service;

    static {
        instance = new DaoImplFactory();
    }

    private DaoImplFactory() {
        daoProperties = new Properties();

        try {
            daoProperties.load(new FileInputStream("C:\\Users\\KFU-user\\Desktop\\JavaItis\\SimpleEnterpriseMaven\\" +
                    "src\\main\\resources\\dao.properties"));
            String daoClassName = daoProperties.getProperty("dao.class");

            usersDao = (UsersDao) Class.forName(daoClassName).newInstance();

            String serviceClassName = daoProperties.getProperty("service.class");

            service = (SimpleUsersService) Class.forName(serviceClassName).newInstance();
            service.setUsersDao(usersDao);

        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static DaoImplFactory getInstance() {
        return instance;
    }

    public UsersDao getUsersDao() {
        return usersDao;
    }

    public Properties getDaoProperties() {
        return daoProperties;
    }

    public SimpleUsersService getService() {
        return service;
    }
}
