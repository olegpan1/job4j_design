package ru.job4j.ood.lsp.parking;

public class RealParking implements Parking {
    @Override
    public boolean addCar(Car car) {
        return false;
    }

    @Override
    public boolean deleteCar(Car car) {
        return false;
    }

    @Override
    public boolean checkSpace(Car car) {
        return false;
    }
}
