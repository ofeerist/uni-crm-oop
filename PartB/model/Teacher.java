package PartB.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import PartB.enums.TeacherType;

public class Teacher extends Employee {

    private int teacherId;
    private TeacherType teacherType;
    private List<Course> courses;

    public Teacher() {
        super();
        this.courses = new ArrayList<>();
    }

    public Teacher(int userId, String firstName, String lastName, String password,
                   String email, String phoneNumber, Date registrationDate,
                   int employeeId, String position,
                   int teacherId, TeacherType teacherType) {

        super(userId, firstName, lastName, password, email, phoneNumber,
              registrationDate, employeeId, position);

        this.teacherId = teacherId;
        this.teacherType = teacherType;
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

    public void viewCourses() {
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public void viewStudents(Course course) {
        for (Student student : course.getStudents()) {
            System.out.println(student);
        }
    }

    public void assignMark(Student student, Course course, Mark mark) {
        System.out.println(firstName + " assigned mark to " +
                student.getFirstName() + " for " + course.getName());
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public TeacherType getTeacherType() {
        return teacherType;
    }

    public void setTeacherType(TeacherType teacherType) {
        this.teacherType = teacherType;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", teacherType=" + teacherType +
                ", position='" + position + '\'' +
                '}';
    }
}
