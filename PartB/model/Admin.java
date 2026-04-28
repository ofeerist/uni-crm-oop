package PartB.model;

import java.util.Date;
import java.util.Objects;

public class Admin extends Employee {

    private final int adminId;

    public Admin(int id, String firstName, String lastName, String email,
                 String password, String phoneNumber, Date registrationDate,
                 int employeeId, String position, int adminId) {

        super(id, firstName, lastName, email, password, phoneNumber,
              registrationDate, employeeId, position);

        this.adminId = adminId;
    }

    public int getAdminId() {
        return adminId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin)) return false;
        if (!super.equals(o)) return false;
        Admin admin = (Admin) o;
        return adminId == admin.adminId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), adminId);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", fullName='" + getFullName() + '\'' +
                ", position='" + getPosition() + '\'' +
                '}';
    }
}