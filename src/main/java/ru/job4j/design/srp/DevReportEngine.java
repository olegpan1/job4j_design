package ru.job4j.design.srp;

import java.util.function.Predicate;

public final class DevReportEngine implements Report {

    public static final String LS = System.lineSeparator();
    private final Store store;

    public DevReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<table>")
                .append(LS)
                .append("<tr>")
                .append(LS)
                .append("<th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th>")
                .append(LS);
        for (Employee employee : store.findBy(filter)) {
            text.append("<tr>").append(LS).append("<td>")
                    .append(employee.getName()).append("</td><td>")
                    .append(employee.getHired()).append("</td><td>")
                    .append(employee.getFired()).append("</td><td>")
                    .append(employee.getSalary()).append("</td>")
                    .append(LS).append("</tr>").append(LS);
        }
        text.append("</table>").append(LS);
        return text.toString();
    }
}
