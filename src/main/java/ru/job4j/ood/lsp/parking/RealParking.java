package ru.job4j.ood.lsp.parking;

import java.util.HashSet;
import java.util.Set;

public class RealParking implements Parking {
    private final Set<Car> vehicles;
    private final Set<Car> trucks;
    private final int countVehicleSpaces;
    private final int countTruckSpaces;
    private int vehicleSpacesFree;
    private int truckSpacesFree;

    public RealParking(int countVehicleSpaces, int countTruckSpaces) {
        this.countVehicleSpaces = countVehicleSpaces;
        this.countTruckSpaces = countTruckSpaces;
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
        if (vehicles.contains(car) || trucks.contains(car)) {
            return false;
        }
        int size = car.getSize();
        boolean freeSpace = checkFreeSpace(size);
        if (freeSpace && size > Vehicle.SIZE && truckSpacesFree >= Vehicle.SIZE) {
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
    public boolean deleteCar(String number) {
        for (Car truck : trucks) {
            if (truck.getNumber().equals(number)) {
                truckSpacesFree++;
                return trucks.remove(truck);
            }
        }
        for (Car vehicle : vehicles) {
            if (vehicle.getNumber().equals(number)) {
                vehicleSpacesFree += vehicle.getSize();
                return vehicles.remove(vehicle);
            }
        }
        return false;
    }

    @Override
    public boolean checkFreeSpace(int size) {
        if (size > Vehicle.SIZE && truckSpacesFree >= Vehicle.SIZE) {
            return true;
        } else {
            return vehicleSpacesFree - size >= 0;
        }
    }
}
