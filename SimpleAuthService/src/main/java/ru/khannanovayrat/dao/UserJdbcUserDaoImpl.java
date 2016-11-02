package ru.khannanovayrat.dao;

import ru.khannanovayrat.models.Car;
import ru.khannanovayrat.models.NewUser;
import ru.khannanovayrat.models.User;
import ru.khannanovayrat.util.Password;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayrat on 25.10.2016.
 */
public class UserJdbcUserDaoImpl implements UserDao {

    //language=SQL
    private static final String USER_SELECT_QUERY =
            "SELECT *" +
                    " FROM auth_user" +
                    " WHERE user_id = ?";

    private static final String USER_ADD_QUERY =
            "INSERT INTO auth_user (fio, password, username)" +
                    " VALUES (?, ?, ?)";

    private  static final String USER_UPDATE_QUERY =
            "UPDATE auth_user" +
                    " SET fio = ?, password = ?, token = ?, username = ?" +
                    " WHERE user_id = ?";

    private static final String USER_SELECT_PASS_USERNAME =
            "SELECT *" +
                    " FROM auth_user" +
                    " WHERE username = ? AND password = ?";

    public static final String ALL_USERS_SQL =
            "SELECT *" +
                    " FROM auth_user";

    private static final String USER_BY_TOKEN_SQL =
            "SELECT *" +
                    " FROM auth_user" +
                    " WHERE token LIKE ?";

    private static final String DELETE_TOKEN_SQL =
            "UPDATE auth_user" +
                    " SET token = ?" +
                    " WHERE token LIKE ?";

    private static final String GET_BY_AGE_SQL =
            "SELECT *" +
                    " FROM auth_user" +
                    " WHERE user_id = ?";

    private final Connection connection;

    public UserJdbcUserDaoImpl(DataSource dataSource) {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
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

    public List<User> getAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ALL_USERS_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<User>();
            while (resultSet.next()){
                users.add(new User(resultSet.getInt("user_id"), resultSet.getString("fio"),
                    resultSet.getString("password"), resultSet.getString("token"),
                        resultSet.getString("username")));
            }
            return users;
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

    public User getUser(String token) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(USER_BY_TOKEN_SQL);
            preparedStatement.setString(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new User(resultSet.getInt("user_id"), resultSet.getString("fio"),
                    resultSet.getString("password"), resultSet.getString("token"),
                    resultSet.getString("username"));
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void deleteToken(String token) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TOKEN_SQL);
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, token);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
