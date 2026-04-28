package PartB.model;

import java.util.Date;
import java.util.Objects;

import PartB.enums.ManagerType;

public class Manager extends Employee {

    private final int managerId;
    private ManagerType managerType;

    public Manager(int id, String firstName, String lastName, String email,
                   String password, String phoneNumber, Date registrationDate,
                   int employeeId, String position,
                   int managerId, ManagerType managerType) {

        super(id, firstName, lastName, email, password, phoneNumber,
              registrationDate, employeeId, position);

        this.managerId = managerId;
        this.managerType = managerType;
    }

    public int getManagerId() {
        return managerId;
    }

    public ManagerType getManagerType() {
        return managerType;
    }

    public void setManagerType(ManagerType managerType) {
        this.managerType = managerType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager)) return false;
        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        return managerId == manager.managerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), managerId);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerId=" + managerId +
                ", fullName='" + getFullName() + '\'' +
                ", managerType=" + managerType +
                ", position='" + getPosition() + '\'' +
                '}';
    }
}