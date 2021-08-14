package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public String getModel() {
        return model;
    }

    public boolean isInStock() {
        return inStock;
    }

    public int getDateOfManufacture() {
        return dateOfManufacture;
    }

    public Contact getContactDealer() {
        return contactDealer;
    }

    public String[] getDescription() {
        return description;
    }

    public static void main(String[] args) {
        final Car car = new Car("Porsche 911", true, 2010,
                new Contact("0-123-456"), "Red Color", "Cabriolet");

        /* JSONObject из json-строки */
        JSONObject jsonContact = new JSONObject("{\"phone\":\"0-123-456\"}");
        System.out.println(jsonContact);

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add(car.getDescription()[0]);
        list.add("Cabriolet");
        JSONArray jsonDescription = new JSONArray(list);
        System.out.println(jsonDescription);

        /* JSONObject напрямую методом put */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("inStock", car.isInStock());
        jsonObject.put("dateOfManufacture", car.getDateOfManufacture());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("description", jsonDescription);

        /* Выведем результат в консоль */
        System.out.println(jsonObject);

        /* Преобразуем объект car в json-строку */
        System.out.println(new JSONObject(car));
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
