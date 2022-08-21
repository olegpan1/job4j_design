package ru.job4j.design.srp;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public record DevReportEngine(Store store) implements Report {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName())
                    .append(System.lineSeparator())
                    .append(DATE_FORMAT.format(employee.getHired().getTime()))
                    .append(System.lineSeparator())
                    .append(DATE_FORMAT.format(employee.getFired().getTime()))
                    .append(System.lineSeparator())
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
