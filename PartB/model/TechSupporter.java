package PartB.model;

import java.util.Date;
import java.util.Objects;

public class TechSupporter extends Employee {

    private final int supportId;

    public TechSupporter(int id, String firstName, String lastName, String email,
                         String password, String phoneNumber, Date registrationDate,
                         int employeeId, String position, int supportId) {

        super(id, firstName, lastName, email, password, phoneNumber,
              registrationDate, employeeId, position);

        this.supportId = supportId;
    }

    public int getSupportId() {
        return supportId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TechSupporter)) return false;
        if (!super.equals(o)) return false;
        TechSupporter that = (TechSupporter) o;
        return supportId == that.supportId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), supportId);
    }

    @Override
    public String toString() {
        return "TechSupporter{" +
                "supportId=" + supportId +
                ", fullName='" + getFullName() + '\'' +
                ", position='" + getPosition() + '\'' +
                '}';
    }
}