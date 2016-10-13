package ru.itis.dao;

import ru.itis.models.Car;
import ru.itis.models.JdbcModel;
import ru.itis.models.Owner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class CarsDaoJdbcImpl implements Dao {

    private Connection connection;

    // language=SQL
    private static final String FIND_QUERY =
            "SELECT owner_id, mileage, car_id" +
                    " FROM test.cars" +
                    " WHERE owner_id = ?";

    // language=SQL
    private static final String FIND_ALL_QUERY =
            "SELECT owner_id, mileage, car_id" +
                    " FROM test.cars";

    //language = SQL
    private static final String DELETE_BY_ID_QUERY =
            "DELETE FROM test.cars" +
                    " WHERE car_id = ?";

    //language = SQL;
    private static final String UPDATE_QUERY =
            "UPDATE test.cars " +
                    " SET mileage = ?, owner_id = ? " +
                    " WHERE car_id = ?";

    //language = SQL
    private static final String INSERT_QUERY =
            "INSERT INTO test.cars (car_id, mileage, owner_id)" +
                    " VALUES (?, ?, ?)";

    public CarsDaoJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    public Car find(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_QUERY);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            result.next();
            return new Car(result.getInt("car_id"), result.getInt("mileage"), result.getInt("owner_id"));
        } catch (SQLException e) {
            System.out.print("not found");
            throw new IllegalArgumentException(e);
        }
    }

    public List<Car> getAll() {
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY);

            ResultSet result = statement.executeQuery();
            List<Car> cars = new ArrayList<Car>();
            while (result.next()) {
                cars.add(new Car(result.getInt("car_id"), result.getInt("mileage"), result.getInt("owner_id")));
            }
            return cars;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @SuppressWarnings("all")
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_QUERY);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void update(JdbcModel obj) {
        try {
            Car car = (Car) obj;
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setInt(1, car.getMileage());
            statement.setInt(2, car.getOwnerId());
            statement.setInt(3, car.getId());
            statement.execute();
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(e);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void add(JdbcModel obj) {
        try {
            Car car = (Car) obj;
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            statement.setInt(1, car.getId());
            statement.setInt(2, car.getMileage());
            statement.setInt(3, car.getOwnerId());
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
