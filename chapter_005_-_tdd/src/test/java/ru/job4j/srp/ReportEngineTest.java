package ru.job4j.srp;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    //    Отдел программистов потребовал ответы в виде html.
    @Test
    public void whenFormatHtml() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportHTML(store);
        StringBuilder expect = new StringBuilder();
        expect.append("<html>");
        expect.append("<head>").append("<title>Report</title>").append("</head>");
        expect.append("<body>");
        expect.append("<table border=\"1\">");
        expect.append("<tr>");
        expect.append("<td>Name</td>");
        expect.append("<td>Hired</td>");
        expect.append("<td>Fired</td>");
        expect.append("<td>Salary</td>");
        expect.append("</tr>");
        expect.append("<tr>");
        expect.append("<td>").append(worker.getName()).append("</td>");
        expect.append("<td>").append(worker.getHired()).append("</td>");
        expect.append("<td>").append(worker.getFired()).append("</td>");
        expect.append("<td>").append(worker.getSalary()).append("</td>");
        expect.append("</tr>");
        expect.append("</table>");
        expect.append("</body>");
        expect.append("</html>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    //    Отдел HR попросил убрать поля даты найма и увольнения.
    @Test
    public void whenOnlyNameAndSalary() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 120);
        store.add(worker);
        Report engine = new ReportOrderBySalaryDesc(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    //    Отдел HR попросил выводить сотрудников в порядке убывания зарплаты
    @Test
    public void whenSortBySalaryDesc() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 120);
        Employee worker2 = new Employee("Anton", now, now, 150);
        store.add(worker1);
        store.add(worker2);
        Report engine = new ReportOrderBySalaryDesc(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    //    Отдел бухгалтерии попросил изменить вид зарплаты.
    @Test
    public void whenSalary() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportChangeSalary(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(" руб.").append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}