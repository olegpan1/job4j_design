package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class RealParkingTest {
    Car truck1 = new Truck(2, "a000aa777");
    Car truck4 = new Truck(2, "a333aa777");
    Car vehicle2 = new Vehicle("c111cc178");
    Car vehicle3 = new Vehicle("e222ee154");

    @Test
    public void whenValidCheckAddDelete() {
        Parking parking = new RealParking(10, 10);
        assertThat(parking.checkFreeSpace(truck1.getSize())).isTrue();
        assertThat(parking.checkFreeSpace(vehicle2.getSize())).isTrue();
        assertThat(parking.addCar(truck1)).isTrue();
        assertThat(parking.addCar(vehicle2)).isTrue();
        assertThat(parking.deleteCar(truck1)).isTrue();
        assertThat(parking.deleteCar(vehicle2)).isTrue();
    }

    @Test
    public void whenOccupiedCheckAddDeleteVehicle() {
        Parking parking = new RealParking(0, 10);
        assertThat(parking.checkFreeSpace(vehicle2.getSize())).isFalse();
        assertThat(parking.addCar(vehicle2)).isFalse();
        assertThat(parking.deleteCar(vehicle2)).isFalse();
    }

    @Test
    public void whenOccupiedCheckAddDeleteTruck() {
        Parking parking = new RealParking(1, 0);
        assertThat(parking.checkFreeSpace(truck1.getSize())).isFalse();
        assertThat(parking.addCar(truck1)).isFalse();
        assertThat(parking.deleteCar(truck1)).isFalse();
    }

    @Test
    public void whenValidSet() {
        RealParking parking = new RealParking(10, 10);
        parking.addCar(truck1);
        parking.addCar(vehicle2);
        parking.addCar(vehicle3);
        assertThat(parking.getVehicles()).containsExactlyInAnyOrder(vehicle2, vehicle3);
        assertThat(parking.getTrucks()).containsExactlyInAnyOrder(truck1);
        parking.deleteCar(truck1);
        assertThat(parking.getTrucks().size()).isZero();
        parking.deleteCar(vehicle2);
        parking.deleteCar(vehicle3);
        assertThat(parking.getVehicles().size()).isZero();
    }

    @Test
    public void whenValidFreeSpaces() {
        RealParking parking = new RealParking(10, 1);
        parking.addCar(truck1);
        assertThat(parking.getTruckSpacesFree()).isZero();
        parking.addCar(truck4);
        assertThat(parking.getVehicleSpacesFree()).isEqualTo(8);
        parking.addCar(vehicle2);
        assertThat(parking.getVehicleSpacesFree()).isEqualTo(7);
        parking.addCar(vehicle3);
        assertThat(parking.getVehicleSpacesFree()).isEqualTo(6);
        parking.deleteCar(truck1);
        assertThat(parking.getTruckSpacesFree()).isEqualTo(1);
        parking.deleteCar(truck4);
        assertThat(parking.getVehicleSpacesFree()).isEqualTo(8);
        parking.deleteCar(vehicle2);
        assertThat(parking.getVehicleSpacesFree()).isEqualTo(9);
        parking.deleteCar(vehicle3);
        assertThat(parking.getVehicleSpacesFree()).isEqualTo(10);
    }
}