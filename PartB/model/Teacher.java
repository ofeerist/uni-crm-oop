package PartB.model;

import java.util.Date;
import java.util.Objects;

import PartB.enums.TeacherType;

public class Teacher extends Employee {

    private final int teacherId;
    private TeacherType teacherType;

    public Teacher(int id, String firstName, String lastName, String email,
                   String password, String phoneNumber, Date registrationDate,
                   int employeeId, String position,
                   int teacherId, TeacherType teacherType) {

        super(id, firstName, lastName, email, password, phoneNumber,
              registrationDate, employeeId, position);

        this.teacherId = teacherId;
        this.teacherType = teacherType;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public TeacherType getTeacherType() {
        return teacherType;
    }

    public void setTeacherType(TeacherType teacherType) {
        this.teacherType = teacherType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return teacherId == teacher.teacherId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), teacherId);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", fullName='" + getFullName() + '\'' +
                ", teacherType=" + teacherType +
                ", position='" + getPosition() + '\'' +
                '}';
    }
}