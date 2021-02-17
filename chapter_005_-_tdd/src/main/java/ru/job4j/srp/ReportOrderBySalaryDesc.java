package ru.job4j.srp;

import java.util.List;
import java.util.function.Predicate;

public class ReportOrderBySalaryDesc implements Report {

    private Store store;

    public ReportOrderBySalaryDesc(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        List<Employee> employees = store.findBy(filter);
        employees.sort(new EmployeeSalaryComparator());
        for (Employee employee : employees) {
            text.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";");
        }
        text.append(System.lineSeparator());
        return text.toString();
    }
}
