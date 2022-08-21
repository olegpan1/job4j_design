package ru.job4j.design.srp;

import com.ibm.icu.text.RuleBasedNumberFormat;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.job4j.design.srp.ReportEngine.DATE_FORMAT;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenDevGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report devReport = new DevReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append(worker.getName())
                .append(System.lineSeparator())
                .append(DATE_FORMAT.format(worker.getHired().getTime()))
                .append(System.lineSeparator())
                .append(DATE_FORMAT.format(worker.getFired().getTime()))
                .append(System.lineSeparator())
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertThat(devReport.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenAccountantGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("en"),
                RuleBasedNumberFormat.SPELLOUT);
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report accReport = new AccountantReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(nf.format(worker.getSalary())).append(";")
                .append(System.lineSeparator());
        assertThat(accReport.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenHRGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 80);
        Employee worker2 = new Employee("Petr", now, now, 100);
        store.add(worker1);
        store.add(worker2);
        Report hrReportReport = new HRReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(hrReportReport.generate(em -> true)).isEqualTo(expect.toString());
    }
}