package ru.job4j.ood.lsp.parking;

public record Vehicle(String number) implements Car {

    public static final int SIZE = 1;

    @Override
    public int getSize() {
        return SIZE;
    }

    @Override
    public String getNumber() {
        return number();
    }
}
