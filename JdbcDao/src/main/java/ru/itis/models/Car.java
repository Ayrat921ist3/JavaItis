package ru.itis.models;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class Car extends JdbcModel  {

    private int id;
    private int mileage;
    private int ownerId;

    public Car(int id, int mileage, int ownerId) {
        this.id = id;
        this.mileage = mileage;
        this.ownerId = ownerId;
    }

    public Car(int mileage, int ownerId) {
        this.mileage = mileage;
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
