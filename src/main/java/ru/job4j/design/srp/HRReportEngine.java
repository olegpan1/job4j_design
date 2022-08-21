package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

public final class HRReportEngine implements Report {
    public static final String LS = System.lineSeparator();
    private final Store store;

    public HRReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> emp = store.findBy(filter);
        emp.sort((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(LS);
        for (Employee employee : emp) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(LS);
        }
        return text.toString();
    }
}
