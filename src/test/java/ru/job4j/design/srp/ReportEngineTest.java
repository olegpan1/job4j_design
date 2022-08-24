package ru.job4j.design.srp;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.job4j.design.srp.AccountantReportEngine.NUMBER_TO_WORD;
import static ru.job4j.design.srp.ReportEngine.DATE_FORMAT;
import static ru.job4j.design.srp.ReportEngine.LS;

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
                .append(LS)
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(LS);
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
                .append("<table>").append(LS)
                .append("<tr>").append(LS)
                .append("<th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th>").append(LS)
                .append("<tr>").append(LS).append("<td>")
                .append(worker.getName()).append("</td><td>")
                .append(worker.getHired()).append("</td><td>")
                .append(worker.getFired()).append("</td><td>")
                .append(worker.getSalary()).append("</td>").append(LS)
                .append("</tr>").append(LS)
                .append("</table>").append(LS);
        assertThat(devReport.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenAccountantGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report accReport = new AccountantReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(LS)
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(NUMBER_TO_WORD.format(worker.getSalary())).append(";")
                .append(LS);
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
                .append(LS)
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(LS)
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(LS);
        assertThat(hrReportReport.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenGeneratedXML() {
        Store store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 80);
        Employee worker2 = new Employee("Petr", now, now, 100);
        store.add(worker1);
        store.add(worker2);
        Report engine = new XMLReportEngine(store);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String date = formatter.format(now.getTime());
        String expect = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees>
                    <employee>
                        <name>Ivan</name>
                        <hired>%s</hired>
                        <fired>%s</fired>
                        <salary>80.0</salary>
                    </employee>
                    <employee>
                        <name>Petr</name>
                        <hired>%s</hired>
                        <fired>%s</fired>
                        <salary>100.0</salary>
                    </employee>
                </employees>
                """.formatted(date, date, date, date);
        assertThat(engine.generate(em -> true)).isEqualTo(expect);
    }

    @Test
    public void whenGeneratedJSON() {
        Store store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 80);
        Employee worker2 = new Employee("Petr", now, now, 100);
        store.add(worker1);
        store.add(worker2);
        String year = String.valueOf(now.get(Calendar.YEAR));
        String month = String.valueOf(now.get(Calendar.MONTH));
        String day = String.valueOf(now.get(Calendar.DAY_OF_MONTH));
        String hour = String.valueOf(now.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(now.get(Calendar.MINUTE));
        String second = String.valueOf(now.get(Calendar.SECOND));
        String expect = "[{\"name\":\"Ivan\",\"hired\":{\"year\":".concat(year)
                .concat(",\"month\":").concat(month).concat(",\"dayOfMonth\":").concat(day)
                .concat(",\"hourOfDay\":").concat(hour).concat(",\"minute\":").concat(minute)
                .concat(",\"second\":").concat(second)
                .concat("},\"fired\":{\"year\":").concat(year)
                .concat(",\"month\":").concat(month).concat(",\"dayOfMonth\":").concat(day)
                .concat(",\"hourOfDay\":").concat(hour).concat(",\"minute\":").concat(minute)
                .concat(",\"second\":").concat(second)
                .concat("},\"salary\":80.0},{\"name\":\"Petr\",\"hired\":{\"year\":").concat(year)
                .concat(",\"month\":").concat(month).concat(",\"dayOfMonth\":").concat(day)
                .concat(",\"hourOfDay\":").concat(hour).concat(",\"minute\":").concat(minute)
                .concat(",\"second\":").concat(second)
                .concat("},\"fired\":{\"year\":").concat(year)
                .concat(",\"month\":").concat(month).concat(",\"dayOfMonth\":").concat(day)
                .concat(",\"hourOfDay\":").concat(hour).concat(",\"minute\":").concat(minute)
                .concat(",\"second\":").concat(second)
                .concat("},\"salary\":100.0}]");
        Report engine = new JSONReportEngine(store);
        assertThat(engine.generate(em -> true)).isEqualTo(expect);
    }
}