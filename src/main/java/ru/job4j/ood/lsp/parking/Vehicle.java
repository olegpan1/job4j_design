package ru.job4j.ood.lsp.parking;

public record Vehicle(String number) implements Car {

    @Override
    public int getSize() {
        return SIZE;
    }

    @Override
    public String getNumber() {
        return number();
    }
}
