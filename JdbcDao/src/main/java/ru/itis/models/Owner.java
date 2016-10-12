package ru.itis.models;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class Owner {

    private String name;
    private int id;
    private String city;
    private int age;

    public Owner(String name, int id, String city, int age) {
        this.name = name;
        this.id = id;
        this.city = city;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("%d %s %d %s", id, city, age, name);
    }
}
