package PartB.model;

import java.util.Date;

public class Admin extends Employee {

    private int adminId;

    public Admin() {
        super();
    }

    public Admin(int userId, String firstName, String lastName, String password,
                 String email, String phoneNumber, Date registrationDate,
                 int employeeId, String position, int adminId) {

        super(userId, firstName, lastName, password, email, phoneNumber,
              registrationDate, employeeId, position);

        this.adminId = adminId;
    }

    public void viewLogs() {
        System.out.println(firstName + " is viewing system logs.");
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
