package ru.khannanovayrat.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.khannanovayrat.models.Car;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KFU-user on 28.10.2016.
 */
public class CarJdbcDaoImpl implements CarDao{

    private NamedParameterJdbcTemplate paramTemplate;
    private JdbcTemplate template;

    private static final String ADD_CAR_SQL =
            "INSERT INTO cars (mileage, fullname, user_id)" +
                    " VALUES (:mileage, :fullname, :userId)";

    private static final String ALL_CARS_SQL =
            "SELECT car_id, mileage, fullname, user_id " +
                    " FROM cars" +
                    " WHERE cars.user_id = :userId";


    public CarJdbcDaoImpl(DataSource dataSource) {
        paramTemplate = new NamedParameterJdbcTemplate(dataSource);
        template = new JdbcTemplate(dataSource);
    }


    public List<Car> getAll(int id){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", id);
        List cars = paramTemplate.queryForList(ALL_CARS_SQL, paramMap);
        return cars;
    }

    public void addCar(Car car) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("mileage", car.getMileage());
        paramMap.put("fullname", car.getModel());
        paramMap.put("userId", car.getOwner_id());
        paramTemplate.execute(ADD_CAR_SQL, paramMap, new PreparedStatementCallback() {
            public Object doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                return preparedStatement.executeUpdate();
            }
        });
    }
}
