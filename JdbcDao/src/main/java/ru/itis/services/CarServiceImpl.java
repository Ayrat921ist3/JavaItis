package ru.itis.services;

import ru.itis.dao.Dao;
import ru.itis.models.Car;

import java.util.List;

/**
 * Created by KFU-user on 13.10.2016.
 */
public class CarServiceImpl implements CarService{

    private Dao carsDao;

    public CarServiceImpl(Dao carsDao) {
        this.carsDao = carsDao;
    }

    public Car findCar(int id){
        return (Car)carsDao.find(id);
    }

    public List<Car> getCars(){
        return carsDao.getAll();
    }

    public void deleteCar(int id){
        carsDao.delete(id);
    }

    public void updateCar(Car car){
        carsDao.update(car);
    }

    public void addCar(Car car){
        carsDao.add(car);
    }
}
