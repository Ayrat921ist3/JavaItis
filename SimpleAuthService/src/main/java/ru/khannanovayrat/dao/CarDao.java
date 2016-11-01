package ru.khannanovayrat.dao;

import ru.khannanovayrat.models.Car;

import java.util.List;

/**
 * Created by KFU-user on 28.10.2016.
 */
public interface CarDao {
    List<Car> getAll(int id);

    void addCar(Car car);
}
