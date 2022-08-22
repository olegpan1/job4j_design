package ru.job4j.ood.srp;

import java.util.Objects;

/**
 * Нарушение SRP: добавление логики проверки логина и пароля при входе validLoginPass()
 */

public class User {

    String login;
    String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean validLoginPass() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(getLogin(), user.getLogin())
                && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getPassword());
    }
}
