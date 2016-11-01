package ru.khannanovayrat.dao;

import ru.khannanovayrat.models.Car;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KFU-user on 28.10.2016.
 */
public class CarJdbcDaoImpl implements CarDao{

    private static final String ADD_CAR_SQL =
            "INSERT INTO cars (mileage, fullname, user_id)" +
                    " VALUES (?, ?, ?)";
    private Connection connection;

    public static final String ALL_CARS_SQL =
            "SELECT car_id, mileage, fullname, user_id " +
                    " FROM cars" +
                    " WHERE cars.user_id = ?";


    public CarJdbcDaoImpl(DataSource dataSource) {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }


    public List<Car> getAll(int id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ALL_CARS_SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Car> userCars = new ArrayList<Car>();
            while(resultSet.next()){
                userCars.add(new Car(resultSet.getInt("car_id"),
                        resultSet.getInt("mileage"), resultSet.getInt("user_id"),
                        resultSet.getString("fullname")));
            }
            return userCars;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void addCar(Car car) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_CAR_SQL);
            preparedStatement.setInt(1, car.getMileage());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setInt(3, car.getOwner_id());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
