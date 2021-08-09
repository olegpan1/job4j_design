package ru.job4j.io.scanner;

import java.util.Objects;

public class UserCSV {
    private String name;
    private int age;
    private String birthDate;
    private String education;
    private int children;

    public UserCSV(String name, int age, String birthDate, String education, int children) {
        this.name = name;
        this.age = age;
        this.birthDate = birthDate;
        this.education = education;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getEducation() {
        return education;
    }

    public int getChildren() {
        return children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserCSV userCSV = (UserCSV) o;
        return getAge() == userCSV.getAge() && getChildren() == userCSV.getChildren() && Objects.equals(getName(), userCSV.getName()) && Objects.equals(getBirthDate(), userCSV.getBirthDate()) && Objects.equals(getEducation(), userCSV.getEducation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getBirthDate(), getEducation(), getChildren());
    }
}
