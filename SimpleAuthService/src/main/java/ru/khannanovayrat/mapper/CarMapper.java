package ru.khannanovayrat.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.khannanovayrat.models.Car;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Ayrat on 08.11.2016.
 */
public class CarMapper implements RowMapper<Car> {
    public Car mapRow(ResultSet resultSet, int i) throws SQLException {

        return new Car(resultSet.getInt("car_id"), resultSet.getInt("mileage"),
                resultSet.getInt("user_id"), resultSet.getString("fullname"));
    }
}
