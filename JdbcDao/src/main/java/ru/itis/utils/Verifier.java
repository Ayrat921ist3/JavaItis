package ru.itis.utils;

import ru.itis.factory.JdbcConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by KFU-user on 13.10.2016.
 */
public class Verifier {

    private static Connection connection;

    static {
        connection = JdbcConnectionFactory.getInstance().getConnection();
    }

    //language = SQL
    private static final String SQL_OWNER = "SELECT * FROM test.owners WHERE owner_id = ?";

    public static void verifyUserExist(int userId){
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_OWNER);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                throw new IllegalArgumentException("USER DOESN'T EXIST");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
