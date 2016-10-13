package ru.itis.services;

import ru.itis.models.Car;

import java.util.List;

/**
 * Created by KFU-user on 13.10.2016.
 */
public interface CarService {
    Car findCar(int id);
    List<Car> getCars();
    void deleteCar(int id);
    void updateCar(Car car);
    void addCar(Car car);
}
