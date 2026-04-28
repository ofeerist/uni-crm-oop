package PartB.model;

import java.util.Date;
import java.util.Objects;

public abstract class Employee extends User {

    private final int employeeId;
    private String position;

    public Employee(int id, String firstName, String lastName, String email,
                    String password, String phoneNumber, Date registrationDate,
                    int employeeId, String position) {

        super(id, firstName, lastName, email, password, phoneNumber, registrationDate);

        this.employeeId = employeeId;
        this.position = position;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), employeeId);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", fullName='" + getFullName() + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}