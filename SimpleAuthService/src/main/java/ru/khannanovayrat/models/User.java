package ru.khannanovayrat.models;

import java.util.Arrays;

/**
 * Created by Ayrat on 25.10.2016.
 */
public class User {

    private int id;
    private String fio;
    private String password;
    private String token;
    private String username;

    public User(int id, String fio, String password, String token, String username) {
        this.id = id;
        this.fio = fio;
        this.password = password;
        this.token = token;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return String.format("%d %s %s %s ", id, fio, password, token);
    }
}
