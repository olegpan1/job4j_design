package ru.job4j.ood.lsp.parking;

public record Truck(int size, String number) implements Car {

    public Truck {
        validateSize(size);
    }

    public void validateSize(int size) {
        if (size <= Vehicle.SIZE) {
            throw new IllegalArgumentException("Wrong truck size!");
        }
    }

    @Override
    public int getSize() {
        return size();
    }

    @Override
    public String getNumber() {
        return number();
    }
}
