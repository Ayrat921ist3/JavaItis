package ru.itis.models;

/**
 * Created by admin on 06.10.2016.
 */
public class User {
    private String name;
    private String password;
    private int age;
    private int id;

    public User(int id, String name, String password, int age) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("%d %s %s %d", id, name, password, age);
    }
}
