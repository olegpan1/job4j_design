package ru.job4j.ood.lsp.parking;

import java.util.Objects;

public class Vehicle extends Car {
    private static final int SIZE = 1;
    private String number;

    public Vehicle(String number) {
        this.size = SIZE;
        this.number = number;
    }

    @Override
    int getSize() {
        return SIZE;
    }

    @Override
    String getNumber() {
        return this.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(getNumber(), vehicle.getNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber());
    }

    @Override
    public String toString() {
        String s = System.lineSeparator();
        return "Vehicle{" + s
                + "size = " + size + s
                + "number = " + number + s
                + '}';
    }
}
