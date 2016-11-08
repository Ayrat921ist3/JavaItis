package ru.khannanovayrat.models;

/**
 * Created by Ayrat on 25.10.2016.
 */
public class Car {
    private int id;
    private int mileage;
    private int owner_id;
    private String model;

    public Car() {
    }

    public Car(int id, int mileage, int owner_id, String model) {
        this.id = id;
        this.mileage = mileage;
        this.owner_id = owner_id;
        this.model = model;
    }

    public Car(int mileage, int owner_id, String model) {
        this.mileage = mileage;
        this.owner_id = owner_id;
        this.model = model;
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

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return String.format("%d %s %d %d", id, model, mileage, owner_id);
    }
}
