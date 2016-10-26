package ru.khannanovayrat.dao;

import ru.khannanovayrat.models.Car;
import ru.khannanovayrat.models.NewUser;
import ru.khannanovayrat.models.User;
import ru.khannanovayrat.util.Password;

import java.sql.*;
import java.util.Arrays;

/**
 * Created by Ayrat on 25.10.2016.
 */
public class UserJdbcDaoImpl implements Dao {

    //language=SQL
    private static final String USER_SELECT_QUERY =
            "SELECT *" +
                    " FROM auth_user" +
                    " WHERE user_id = ?";

    //language = SQL
    private static final String USER_ADD_QUERY =
            "INSERT INTO auth_user (fio, password, username)" +
                    " VALUES (?, ?, ?)";

    //language = SQL
    private  static final String USER_UPDATE_QUERY =
            "UPDATE auth_user" +
                    " SET fio = ?, password = ?, token = ?, username = ?" +
                    " WHERE user_id = ?";
    //language = SQL
    private static final String USER_SELECT_PASS_USERNAME =
            "SELECT *" +
                    " FROM auth_user" +
                    " WHERE username = ? AND password = ?";

    private final Connection connection;

    public UserJdbcDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public User getUser(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(USER_SELECT_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            return new User(result.getInt("user_id"), result.getString("fio"),
                    result.getString("password"), result.getString("token"),
                    result.getString("username"));
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void saveUser(NewUser user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(USER_ADD_QUERY);
            preparedStatement.setString(1, user.getFio());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void deleteUser(int id) {

    }

    public void addUserCar(Car car) {

    }

    public void update(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(USER_UPDATE_QUERY);
            preparedStatement.setString(1, user.getFio());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getToken());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setInt(5, user.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public User getUser(String username, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(USER_SELECT_PASS_USERNAME);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, Password.hash(password));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new User(resultSet.getInt("user_id"), resultSet.getString("fio"),
                    resultSet.getString("password"), resultSet.getString("token"),
                    resultSet.getString("username"));
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
