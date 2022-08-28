package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class RealParkingTest {
    private Set<Car> vehicles;
    private Set<Car> trucks;
    Car truck1 = new Truck(2, "a000aa777");
    Car truck4 = new Truck(2, "a000aa777");
    Car vehicle2 = new Vehicle("c111cc178");
    Car vehicle3 = new Vehicle("e222ee154");

    @Test
    public void whenValidCheckAddDelete() {
        Parking parking = new RealParking(10, 10);
        assertThat(parking.checkSpace(truck1)).isTrue();
        assertThat(parking.checkSpace(vehicle2)).isTrue();
        assertThat(parking.addCar(truck1)).isTrue();
        assertThat(parking.addCar(vehicle2)).isTrue();
        assertThat(parking.deleteCar(truck1)).isTrue();
        assertThat(parking.deleteCar(vehicle2)).isTrue();
    }

    @Test
    public void whenOccupiedCheckAddDeleteVehicle() {
        Parking parking = new RealParking(0, 10);
        assertThat(parking.checkSpace(vehicle2)).isFalse();
        assertThat(parking.addCar(vehicle2)).isFalse();
        assertThat(parking.deleteCar(vehicle2)).isFalse();
    }

    @Test
    public void whenOccupiedCheckAddDeleteTruck() {
        Parking parking = new RealParking(1, 0);
        assertThat(parking.checkSpace(truck1)).isFalse();
        assertThat(parking.addCar(truck1)).isFalse();
        assertThat(parking.deleteCar(truck1)).isFalse();
    }

    @Test
    public void whenValidSet() {
        Parking parking = new RealParking(10, 10);
        parking.addCar(truck1);
        parking.addCar(vehicle2);
        parking.addCar(vehicle3);
        assertThat(vehicles).containsExactlyInAnyOrder(vehicle2, vehicle3);
        assertThat(trucks).containsExactlyInAnyOrder(truck1);
        parking.deleteCar(truck1);
        parking.deleteCar(vehicle2);
        parking.deleteCar(vehicle3);
        assertThat(vehicles.size()).isZero();
        assertThat(trucks.size()).isZero();
    }

    @Test
    public void whenValidFreeSpaces() {
        RealParking parking = new RealParking(10, 1);
        parking.addCar(truck1);
        parking.addCar(truck4);
        assertThat(parking.getTruckSpacesFree()).isZero();
        assertThat(parking.getVehicleSpacesFree()).isEqualTo(8);
        parking.addCar(vehicle2);
        parking.addCar(vehicle3);
        assertThat(parking.getTruckSpacesFree()).isZero();
        assertThat(parking.getVehicleSpacesFree()).isEqualTo(6);
        parking.deleteCar(truck1);
        parking.deleteCar(truck4);
        assertThat(parking.getTruckSpacesFree()).isEqualTo(1);
        assertThat(parking.getVehicleSpacesFree()).isEqualTo(8);
        parking.deleteCar(vehicle2);
        parking.deleteCar(vehicle3);
        assertThat(parking.getTruckSpacesFree()).isZero();
        assertThat(parking.getVehicleSpacesFree()).isEqualTo(10);

    }
}