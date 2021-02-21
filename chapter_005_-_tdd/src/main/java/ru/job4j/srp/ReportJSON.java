package ru.job4j.srp;

import java.util.function.Predicate;

public class ReportJSON implements Report {

    private Store store;

    public ReportJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder json = new StringBuilder();
        json.append("[")
                .append(System.lineSeparator())
                .append(addEmployees(filter))
                .append("]");
        return json.toString();
    }

    private String addEmployees(Predicate<Employee> filter) {
        StringBuilder employees = new StringBuilder();
        for (Employee employee : store.findBy(filter)) {
            employees.append("{")
                    .append(System.lineSeparator())
                    .append("\"Name\": \"").append(employee.getName()).append("\",")
                    .append(System.lineSeparator())
                    .append("\"Hired\": \"").append(employee.getHired()).append("\",")
                    .append(System.lineSeparator())
                    .append("\"Fired\": \"").append(employee.getFired()).append("\",")
                    .append(System.lineSeparator())
                    .append("\"Salary\": \"").append(employee.getSalary()).append("\"")
                    .append(System.lineSeparator())
                    .append("}")
                    .append(System.lineSeparator());
        }
        return employees.toString();
    }
}
