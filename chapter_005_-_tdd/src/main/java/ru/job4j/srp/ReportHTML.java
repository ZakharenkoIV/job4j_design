package ru.job4j.srp;

import java.util.function.Predicate;

public class ReportHTML implements Report {

    private Store store;

    public ReportHTML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder report = new StringBuilder();
        report.append("<html>");
        report.append("<head>").append("<title>Report</title>").append("</head>");
        report.append("<body>");
        report.append("<table border=\"1\">");
        report.append(addHeadLine());
        report.append(addEmployees(filter));
        report.append("</table>");
        report.append("</body>");
        report.append("</html>");
        return report.toString();
    }

    private String addEmployees(Predicate<Employee> filter) {
        StringBuilder report = new StringBuilder();
        for (Employee employee : store.findBy(filter)) {
            report.append("<tr>");
            report.append("<td>").append(employee.getName()).append("</td>");
            report.append("<td>").append(employee.getHired()).append("</td>");
            report.append("<td>").append(employee.getFired()).append("</td>");
            report.append("<td>").append(employee.getSalary()).append("</td>");
            report.append("</tr>");
        }
        return report.toString();
    }

    private String addHeadLine() {
        StringBuilder report = new StringBuilder();
        report.append("<tr>");
        report.append("<td>Name</td>");
        report.append("<td>Hired</td>");
        report.append("<td>Fired</td>");
        report.append("<td>Salary</td>");
        report.append("</tr>");
        return report.toString();
    }
}
