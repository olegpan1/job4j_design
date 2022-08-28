package ru.job4j.ood.lsp.parking;

public interface Parking {
    boolean addCar(Car car);
    boolean deleteCar(Car car);
    boolean checkFreeSpace(int size);
}
