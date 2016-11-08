package ru.khannanovayrat.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.khannanovayrat.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by Ayrat on 08.11.2016.
 */
public class UserMapper implements RowMapper<User> {

    public User mapRow(ResultSet rs, int i) throws SQLException {

        return new User(rs.getInt("user_id"), rs.getString("fio"),
                rs.getString("password"), rs.getString("token"),
                rs.getString("username"));
    }
}
