package ru.job4j.ood.lsp.parking;

import java.util.HashSet;
import java.util.Set;

public class RealParking implements Parking {
    private final Set<Car> vehicles;
    private final Set<Car> trucks;
    private int vehicleSpacesFree;
    private int truckSpacesFree;

    public RealParking(int countVehicleSpaces, int countTruckSpaces) {
        this.vehicleSpacesFree = countVehicleSpaces;
        this.truckSpacesFree = countTruckSpaces;
        this.vehicles = new HashSet<>();
        this.trucks = new HashSet<>();
    }

    public Set<Car> getVehicles() {
        return new HashSet<>(vehicles);
    }

    public Set<Car> getTrucks() {
        return new HashSet<>(trucks);
    }

    public int getVehicleSpacesFree() {
        return vehicleSpacesFree;
    }

    public int getTruckSpacesFree() {
        return truckSpacesFree;
    }

    @Override
    public boolean addCar(Car car) {
        int size = car.getSize();
        boolean freeSpace = checkFreeSpace(size);
        if (freeSpace && size > 1 && truckSpacesFree >= 1) {
            truckSpacesFree--;
            trucks.add(car);
            return true;
        } else if (vehicleSpacesFree - size >= 0) {
            vehicleSpacesFree = vehicleSpacesFree - size;
            vehicles.add(car);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCar(Car car) {
        if (trucks.contains(car)) {
            truckSpacesFree++;
            return trucks.remove(car);
        } else if (vehicles.contains(car)) {
            vehicleSpacesFree += car.getSize();
            return vehicles.remove(car);
        }
        return false;
    }

    @Override
    public boolean checkFreeSpace(int size) {
        if (size > 1 && truckSpacesFree >= 1) {
            return true;
        } else {
            return vehicleSpacesFree - size >= 0;
        }
    }
}
