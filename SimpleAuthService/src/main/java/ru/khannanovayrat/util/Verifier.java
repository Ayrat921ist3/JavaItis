package ru.khannanovayrat.util;

import ru.khannanovayrat.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Ayrat on 26.10.2016.
 */
public class Verifier {

    private static Connection connection;

    static {
        connection = ConnectionFactory.getInstance().getConnection();
    }

    //language = SQL
    private static final String CHECK_USERNAME_SQL =
            "SELECT username" +
            " FROM auth_user" +
                    " WHERE username = ?";
    //language = SQL
    private static final String CHECk_USER_SQL =
            "SELECT username, password" +
                    " FROM auth_user" +
                    " WHERE username = ? AND password = ?";

    //language = SQL
    public static final String CHECK_TOKEN_SQL =
            "SELECT token" +
                    " FROM auth_user" +
                    " WHERE token LIKE ?";

    public static boolean verifyUsernameExist(String username){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USERNAME_SQL);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static boolean verifyUserExists(String username, String password){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CHECk_USER_SQL);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, Password.hash(password));
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static boolean verifyTokenValid(String token){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_TOKEN_SQL);
            preparedStatement.setString(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
