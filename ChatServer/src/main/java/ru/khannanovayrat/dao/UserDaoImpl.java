package ru.khannanovayrat.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.khannanovayrat.mapper.UserMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    private NamedParameterJdbcTemplate paramTemplate;

    private static final String CREATE_USER_SQL =
            "INSERT INTO chat_users (name)" +
                    " VALUES (:name)";

    public UserDaoImpl(@Autowired DataSource dataSource){
        paramTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    public void createUser(String username) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", username);
        paramTemplate.execute(CREATE_USER_SQL, paramMap,
                PreparedStatement::executeUpdate);
    }
}
