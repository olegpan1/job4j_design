package ru.job4j.ood.lsp.parking;

import java.util.Set;

public class RealParking implements Parking {
    private final int countVehicleSpaces;
    private final int countTruckSpaces;
    private Set<Car> vehicles;
    private Set<Car> trucks;
    private int vehicleSpacesFree;
    private int truckSpacesFree;

    public RealParking(int countVehicleSpaces, int countTruckSpaces) {
        this.countVehicleSpaces = countVehicleSpaces;
        this.countTruckSpaces = countTruckSpaces;
        this.vehicleSpacesFree = countVehicleSpaces;
        this.truckSpacesFree = countTruckSpaces;
    }

    public void setVehicleSpacesFree(int vehicleSpacesFree) {
        this.vehicleSpacesFree = vehicleSpacesFree;
    }

    public void setTruckSpacesFree(int truckSpacesFree) {
        this.truckSpacesFree = truckSpacesFree;
    }

    public int getCountVehicleSpaces() {
        return countVehicleSpaces;
    }

    public int getCountTruckSpaces() {
        return countTruckSpaces;
    }

    public int getVehicleSpacesFree() {
        return vehicleSpacesFree;
    }

    public int getTruckSpacesFree() {
        return truckSpacesFree;
    }

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
