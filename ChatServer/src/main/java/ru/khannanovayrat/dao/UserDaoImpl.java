package ru.khannanovayrat.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.khannanovayrat.dto.UserDto;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    private NamedParameterJdbcTemplate paramTemplate;

    private static final String CREATE_USER_SQL =
            "INSERT INTO chat_user (name, hash_password, login)" +
                    " VALUES (:name, :hash_password, :login)";

    public UserDaoImpl(@Autowired DataSource dataSource){
        paramTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    public void createUser(UserDto user) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", user.getName());

        //TODO implement hashing
        int hashPassword = user.getPassword().hashCode();
        paramMap.put("hash_password", hashPassword);
        paramMap.put("login", user.getLogin());
        paramTemplate.execute(CREATE_USER_SQL, paramMap,
                PreparedStatement::executeUpdate);
    }
}
