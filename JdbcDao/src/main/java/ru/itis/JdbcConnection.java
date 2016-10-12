package ru.itis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class JdbcConnection {

    private static JdbcConnection instance;
    private Connection connection;
    private static final String DATA_BASE_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DATA_BASE_URL_HOME = "jdbc:postgresql://localhost:5432/usersDB";
    private static final String USER_DB_NAME = "postgres";
    private static final String USER_DB_PASSWORD = "Vypapos88";

    static {
        instance = new JdbcConnection();
    }

    private JdbcConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DATA_BASE_URL, USER_DB_NAME, USER_DB_PASSWORD);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public static JdbcConnection getInstance(){
        return instance;
    }
}
