package ru.khannanovayrat.model;

/**
 * Created by Ayrat on 06.12.2016.
 */
public class Auth {

    private int userId;
    private String token;

    public Auth() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
