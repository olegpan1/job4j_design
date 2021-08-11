package ru.job4j.serialization.json.xml;

import ru.job4j.serialization.json.Car;
import ru.job4j.serialization.json.Contact;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        Car car = new Car("Porsche 911", true, 2005,
                new Contact("0-123-456"), "Red Color", "Cabriolet");
        // Получаем контекст для доступа к АПИ
        JAXBContext context = JAXBContext.newInstance(Car.class);
        // Создаем сериализатор
        Marshaller marshaller = context.createMarshaller();
        // Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            // Сериализуем
            marshaller.marshal(car, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        // Для десериализации нам нужно создать десериализатор
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            // десериализуем
            Car result = (Car) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

    }
}