package ru.khannanovayrat.service;

import org.springframework.stereotype.Service;
import ru.khannanovayrat.dao.CarDao;
import ru.khannanovayrat.models.Car;

import java.util.List;

/**
 * Created by Ayrat on 01.11.2016.
 */
@Service
public class CarService {

    private CarDao carDao;

    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    public List<Car> getAll(int id){
        return carDao.getAll(id);
    }

    public void addCar(Car car) {
        carDao.addCar(car);
    }
}
