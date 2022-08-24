package ru.job4j.design.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.function.Predicate;

public final class XMLReportEngine implements Report {

    private Store store;

    public XMLReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder xml = new StringBuilder();
        try (StringWriter writer = new StringWriter()) {
            JAXBContext context = JAXBContext.newInstance(EmployeeData.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(new EmployeeData(store.findBy(filter)), writer);
            xml.append(writer.getBuffer().toString());
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return xml.toString();
    }
}