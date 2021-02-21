package ru.job4j.srp;

import java.util.function.Predicate;

public class ReportXML implements Report {

    private Store store;

    public ReportXML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder report = new StringBuilder();
        report.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(System.lineSeparator());
        report.append("<report>").append(System.lineSeparator());
        report.append("<employees>").append(System.lineSeparator());
        report.append(addEmployees(filter));
        report.append("</employees>").append(System.lineSeparator());
        report.append("</report>");
        System.out.println(report.toString());
        return report.toString();
    }

    private String addEmployees(Predicate<Employee> filter) {
        StringBuilder report = new StringBuilder();
        for (Employee employee : store.findBy(filter)) {
            report.append("<employee ");
            report.append("Name=\"").append(employee.getName()).append("\" ");
            report.append("Hired=\"").append(employee.getHired()).append("\" ");
            report.append("Fired=\"").append(employee.getFired()).append("\" ");
            report.append("Salary=\"").append(employee.getSalary()).append("\" ");
            report.append("/>").append(System.lineSeparator());
        }
        return report.toString();
    }
}
