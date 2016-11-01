package ru.khannanovayrat.models;

import ru.khannanovayrat.util.Password;

/**
 * Created by Ayrat on 25.10.2016.
 */
public class NewUser {

    private String fio;
    private String password;
    private String username;

    public NewUser(String fio, String password, String username) {
        this.fio = fio;
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return String.format("%s, %s, %s", fio, Password.hash(password), username);
    }
}
