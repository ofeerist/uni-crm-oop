package PartB.model;

import java.util.Date;
import java.util.Objects;

public class Student extends User {

    private final int studentId;
    private final int yearOfStudy;
    private final double gpa;
    private final int credits;

    public Student(int id, String firstName, String lastName, String email,
                   String password, String phoneNumber, Date registrationDate,
                   int studentId, int yearOfStudy, double gpa, int credits) {

        super(id, firstName, lastName, email, password, phoneNumber, registrationDate);

        this.studentId = studentId;
        this.yearOfStudy = yearOfStudy;
        this.gpa = gpa;
        this.credits = credits;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public double getGpa() {
        return gpa;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return studentId == student.studentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), studentId);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", fullName='" + getFullName() + '\'' +
                ", yearOfStudy=" + yearOfStudy +
                ", gpa=" + gpa +
                ", credits=" + credits +
                '}';
    }
}