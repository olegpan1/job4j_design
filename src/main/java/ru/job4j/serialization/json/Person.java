package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Person {
    private final boolean sex;
    private final int age;
    private final Contact contact;
    private final String[] statuses;

    public boolean isSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getStatuses() {
        return statuses;
    }

    public Person(boolean sex, int age, Contact contact, String... statuses) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;

    }

    @Override
    public String toString() {
        return "Person{"
                + "sex=" + sex
                + ", age=" + age
                + ", contact=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }

    public static void main(String[] args) {
        final Car car = new Car("Porsche 911", true, 2010,
                new Contact("0-123-456"), "Red Color", "Cabriolet");

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));

        final String carJson = "{"
                + "\"inStock\":false,"
                + "\"dateOfManufacture\":2020,"
                + "\"contactDealer\":"
                + "{\"phone\":\"0-123-789\"},"
                + "\"description\":"
                + "[\"Green Color\",\"Cabriolet\"]"
                + "}";

        final Car carMod = gson.fromJson(carJson, Car.class);
        System.out.println(carMod);
    }

}
