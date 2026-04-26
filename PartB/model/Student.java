package PartB.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student extends User {

    protected int studentId;
    protected int yearOfStudy;
    protected double gpa;
    protected int credits;
    protected List<Course> courses;

    public Student() {
        super();
        this.courses = new ArrayList<>();
    }

    public Student(int userId, String firstName, String lastName, String password,
                   String email, String phoneNumber, Date registrationDate,
                   int studentId, int yearOfStudy, double gpa, int credits) {

        super(userId, firstName, lastName, password, email, phoneNumber, registrationDate);

        this.studentId = studentId;
        this.yearOfStudy = yearOfStudy;
        this.gpa = gpa;
        this.credits = credits;
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void viewCourses() {
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public void viewMarks() {
        System.out.println(firstName + " is viewing marks.");
    }

    public void viewTranscript() {
        System.out.println(firstName + " is viewing transcript.");
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gpa=" + gpa +
                ", yearOfStudy=" + yearOfStudy +
                ", credits=" + credits +
                '}';
    }
}