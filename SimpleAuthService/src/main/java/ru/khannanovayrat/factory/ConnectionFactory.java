package ru.khannanovayrat.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Ayrat on 25.10.2016.
 */
public class ConnectionFactory {
    private static ConnectionFactory instance;
    private Connection connection;

    static {
        instance = new ConnectionFactory();
    }

    private ConnectionFactory() {
        Properties properties = new Properties();
        try {
//            properties.load(new FileInputStream("C:\\Users\\Ayrat\\Desktop\\JavaItis" +
//                    "\\SimpleAuthService\\src\\main\\resources\\connection.properties"));
            properties.load(new FileInputStream("C:\\Users\\KFU-user\\Desktop\\JavaItis" +
                    "\\SimpleAuthService\\src\\main\\resources\\connection.properties"));
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            String driver = properties.getProperty("db.driver");
//            String url = properties.getProperty("db.url");
            String url = properties.getProperty("db.url_itis");
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static ConnectionFactory getInstance(){
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }
}
