package PartB.model;

import java.util.ArrayList;
import java.util.List;

import PartB.enums.CourseType;

public class Course {

    private int courseId;
    private String name;
    private int credits;
    private CourseType courseType;
    private Teacher teacher;
    private List<Student> students;

    public Course() {
        this.students = new ArrayList<>();
    }

    public Course(int courseId, String name, int credits, CourseType courseType) {
        this.courseId = courseId;
        this.name = name;
        this.credits = credits;
        this.courseType = courseType;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void assignTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void viewCourseInfo() {
        System.out.println(this);
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", name='" + name + '\'' +
                ", credits=" + credits +
                ", courseType=" + courseType +
                ", teacher=" + (teacher != null ? teacher.getFirstName() : "None") +
                '}';
    }
}
