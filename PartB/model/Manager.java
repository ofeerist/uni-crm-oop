package PartB.model;

import java.util.Date;

import PartB.enums.ManagerType;

public class Manager extends Employee {

    private int managerId;
    private ManagerType managerType;

    public Manager() {
        super();
    }

    public Manager(int userId, String firstName, String lastName, String password,
                   String email, String phoneNumber, Date registrationDate,
                   int employeeId, String position,
                   int managerId, ManagerType managerType) {

        super(userId, firstName, lastName, password, email, phoneNumber,
              registrationDate, employeeId, position);

        this.managerId = managerId;
        this.managerType = managerType;
    }

    public void approveRegistration(Student student, Course course) {
        System.out.println(firstName + " approved registration of " +
                student.getFirstName() + " for " + course.getName());
    }

    public void assignCourseToTeacher(Course course, Teacher teacher) {
        course.assignTeacher(teacher);
        teacher.addCourse(course);

        System.out.println(firstName + " assigned " +
                course.getName() + " to " + teacher.getFirstName());
    }

    public void createReport() {
        System.out.println(firstName + " created academic report.");
    }

    public void viewUserInfo(User user) {
        System.out.println(user);
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public ManagerType getManagerType() {
        return managerType;
    }

    public void setManagerType(ManagerType managerType) {
        this.managerType = managerType;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerId=" + managerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", managerType=" + managerType +
                ", position='" + position + '\'' +
                '}';
    }
}
