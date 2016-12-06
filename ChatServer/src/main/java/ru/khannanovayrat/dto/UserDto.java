package ru.khannanovayrat.dto;

/**
 * Created by Ayrat on 06.12.2016.
 */
public class UserDto {

    private String name;
    private String login;
    private String password;

    public UserDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
