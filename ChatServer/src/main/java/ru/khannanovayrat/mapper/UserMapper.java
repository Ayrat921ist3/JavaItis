package ru.khannanovayrat.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.khannanovayrat.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by KFU-user on 24.11.2016.
 */
public class UserMapper implements RowMapper<User> {

    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        return new User(resultSet.getInt("id"),
                resultSet.getString("name"));
    }
}
