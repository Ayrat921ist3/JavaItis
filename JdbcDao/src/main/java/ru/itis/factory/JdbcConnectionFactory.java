package ru.itis.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class JdbcConnectionFactory {

    private static JdbcConnectionFactory instance;
    private Connection connection;
    Properties properties;
    private static final String DATA_BASE_URL_HOME = "jdbc:postgresql://localhost:5432/usersDB";

    static {
        instance = new JdbcConnectionFactory();
    }

    private JdbcConnectionFactory() {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("C:\\Users\\KFU-user\\Desktop\\JavaItis\\JdbcDao\\src\\main\\resources\\connection.properties"));
            String driver = properties.getProperty("jdbc.driver");
            String url = properties.getProperty("jdbc.db_url");
            String password = properties.getProperty("jdbc.password");
            String username = properties.getProperty("jdbc.username");
            System.out.println("=" + driver + "=");
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e){
            throw new IllegalArgumentException(e);
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public static JdbcConnectionFactory getInstance(){
        return instance;
    }
}
