package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;
import java.util.Arrays;
import java.util.Objects;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    @XmlAttribute
    private String model;
    private boolean inStock;
    private int dateOfManufacture;
    private Contact contactDealer;
    @XmlElementWrapper(name = "descriptions")
    @XmlElement(name = "description")
    private String[] description;

    public Car() {
    }

    public Car(String model, boolean inStock, int dateOfManufacture, Contact contactDealer, String... description) {
        this.model = model;
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
        return inStock == car.inStock && dateOfManufacture == car.dateOfManufacture && Objects.equals(model, car.model) && Objects.equals(contactDealer, car.contactDealer) && Arrays.equals(description, car.description);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(model, inStock, dateOfManufacture, contactDealer);
        result = 31 * result + Arrays.hashCode(description);
        return result;
    }

    @Override
    public String toString() {
        return "Car{"
                + "model='" + model + '\''
                + ", inStock=" + inStock
                + ", dateOfManufacture=" + dateOfManufacture
                + ", contactDealer=" + contactDealer
                + ", description=" + Arrays.toString(description)
                + '}';
    }
}
