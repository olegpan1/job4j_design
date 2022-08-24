package ru.job4j.design.srp;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "employees")
public class EmployeeData {
    private List<Employee> employee = new ArrayList<>();

    public EmployeeData() {
    }

    public EmployeeData(List<Employee> employee) {
        this.employee = employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    public List<Employee> getEmployee() {
        return employee;
    }
}