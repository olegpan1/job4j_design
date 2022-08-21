package ru.job4j.design.srp;

import com.ibm.icu.text.RuleBasedNumberFormat;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.function.Predicate;

public final class AccountantReportEngine implements Report {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");
    public static final RuleBasedNumberFormat NUMBER_TO_WORD = new RuleBasedNumberFormat(Locale.forLanguageTag("en"),
            RuleBasedNumberFormat.SPELLOUT);
    public static final String LS = System.lineSeparator();
    private final Store store;

    public AccountantReportEngine(Store store) {
        this.store = store;
    }


    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(LS);
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(NUMBER_TO_WORD.format(employee.getSalary())).append(";")
                    .append(LS);
        }
        return text.toString();
    }
}
