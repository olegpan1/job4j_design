package ru.job4j.serialization.json;

import java.util.Arrays;
import java.util.Objects;

public class Car {
    private final boolean inStock;
    private final int dateOfManufacture;
    private final Contact contactDealer;
    private final String[] description;

    public Car(boolean inStock, int dateOfManufacture, Contact contactDealer, String... description) {
        this.inStock = inStock;
        this.dateOfManufacture = dateOfManufacture;
        this.contactDealer = contactDealer;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return inStock == car.inStock && dateOfManufacture == car.dateOfManufacture && Objects.equals(contactDealer, car.contactDealer) && Arrays.equals(description, car.description);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(inStock, dateOfManufacture, contactDealer);
        result = 31 * result + Arrays.hashCode(description);
        return result;
    }

    @Override
    public String toString() {
        return "Car{"
                + "inStock=" + inStock
                + ", dateOfManufacture=" + dateOfManufacture
                + ", contactDealer=" + contactDealer
                + ", description=" + Arrays.toString(description)
                + '}';
    }
}
