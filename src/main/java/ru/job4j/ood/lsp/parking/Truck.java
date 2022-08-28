package ru.job4j.ood.lsp.parking;

import java.util.Objects;

public class Truck extends Car {
    private int size;
    private String number;

    public Truck(int size, String number) {
        validate(size);
        this.size = size;
        this.number = number;
    }

    private void validate(int size) {
        if (size <= 1) {
            throw new IllegalArgumentException("Wrong truck size!");
        }
    }

    @Override
    int getSize() {
        return this.size;
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
        Truck truck = (Truck) o;
        return getSize() == truck.getSize()
                && Objects.equals(getNumber(), truck.getNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSize(), getNumber());
    }

    @Override
    public String toString() {
        String s = System.lineSeparator();
        return "Truck{" + s
                + "size = " + size + s
                + "number = " + number + s
                + '}';
    }
}
