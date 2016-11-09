package ru.khannanovayrat.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.metadata.OracleCallMetaDataProvider;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.khannanovayrat.mapper.UserMapper;
import ru.khannanovayrat.models.Car;
import ru.khannanovayrat.models.NewUser;
import ru.khannanovayrat.models.User;
import ru.khannanovayrat.util.Password;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Ayrat on 25.10.2016.
 */
public class UserJdbcUserDaoImpl implements UserDao {

    private NamedParameterJdbcTemplate paramTemplate;
    private JdbcTemplate template;
    private static Logger log = Logger.getLogger(UserJdbcUserDaoImpl.class.getName());

    //language=SQL
    private static final String USER_SELECT_QUERY =
            "SELECT *" +
                    " FROM auth_user" +
                    " WHERE user_id = :userId";

    private static final String USER_ADD_QUERY =
            "INSERT INTO auth_user (fio, password, username)" +
                    " VALUES (:fio, :password, :username)";

    private  static final String USER_UPDATE_QUERY =
            "UPDATE auth_user" +
                    " SET fio = :fio, password = :password, token = :token, username = :username" +
                    " WHERE user_id = :user_id";

    private static final String USER_SELECT_PASS_USERNAME =
            "SELECT *" +
                    " FROM auth_user" +
                    " WHERE username = :username AND password = :password";

    public static final String ALL_USERS_SQL =
            "SELECT *" +
                    " FROM auth_user";

    private static final String USER_BY_TOKEN_SQL =
            "SELECT *" +
                    " FROM auth_user" +
                    " WHERE token LIKE :token";

    private static final String DELETE_TOKEN_SQL =
            "UPDATE auth_user" +
                    " SET token = :newToken" +
                    " WHERE token LIKE :token";

    private static final String GET_BY_AGE_SQL =
            "SELECT *" +
                    " FROM auth_user" +
                    " WHERE user_id = ?";


    public UserJdbcUserDaoImpl(DataSource dataSource) {
        paramTemplate = new NamedParameterJdbcTemplate(dataSource);
        template = new JdbcTemplate(dataSource);
    }


    public User getUser(int id) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("userId", id);
        return paramTemplate.queryForObject(USER_SELECT_QUERY, paramsMap, new UserMapper());
    }

    public void saveUser(NewUser user) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("fio", user.getFio());
        paramsMap.put("username", user.getUsername());
        paramsMap.put("password", user.getPassword());
        paramTemplate.execute(USER_ADD_QUERY, paramsMap, new PreparedStatementCallback() {
            public Object doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                return preparedStatement.executeUpdate();
            }
        });
    }

    public void deleteUser(int id) {

    }

    public void addUserCar(Car car) {

    }

    public void update(User user) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("fio", user.getFio());
        paramsMap.put("password", user.getPassword());
        paramsMap.put("token", user.getToken());
        paramsMap.put("username", user.getUsername());
        paramsMap.put("user_id", user.getId());
        paramTemplate.execute(USER_UPDATE_QUERY, paramsMap, new PreparedStatementCallback() {
            public Object doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                return preparedStatement.executeUpdate();
            }
        });
    }

    public List<User> getAll() {
        List list = template.queryForList(ALL_USERS_SQL);
        return list;
    }

    public User getUser(String username, String password) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("username", username);
        paramMap.put("password", Password.hash(password));
        return paramTemplate.queryForObject(USER_SELECT_PASS_USERNAME, paramMap, new UserMapper());
    }

    public User getUser(String token) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("token", token);
        return paramTemplate.queryForObject(USER_BY_TOKEN_SQL, paramMap, new UserMapper());
    }

    public void deleteToken(String token) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("token", token);
        paramMap.put("newToken", null);
        paramTemplate.execute(DELETE_TOKEN_SQL, paramMap, new PreparedStatementCallback() {
            public Object doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                return preparedStatement.executeUpdate();
            }
        });
    }
}
