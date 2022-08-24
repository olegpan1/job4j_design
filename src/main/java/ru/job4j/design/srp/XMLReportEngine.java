package ru.job4j.design.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.function.Predicate;

public final class XMLReportEngine implements Report {

    private Store store;
    JAXBContext context;
    Marshaller marshaller;


    public XMLReportEngine(Store store) {
        this.store = store;
        try {
            this.context = JAXBContext.newInstance(EmployeeData.class);
            this.marshaller = context.createMarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder xml = new StringBuilder();
        try (StringWriter writer = new StringWriter()) {
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(new EmployeeData(store.findBy(filter)), writer);
            xml.append(writer.getBuffer().toString());
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return xml.toString();
    }
}