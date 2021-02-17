package ru.job4j.srp;

import java.util.function.Predicate;

public class ReportChangeSalary implements Report {

    private Store store;

    public ReportChangeSalary(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        for (Employee employee : store.findBy(filter)) {
            text.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(changeSalary(employee.getSalary())).append(";");
        }
        text.append(System.lineSeparator());
        return text.toString();
    }

    private String changeSalary(double salary) {
        return "".concat(Double.valueOf(salary).toString()).concat(" руб.");
    }
}
