package kz.edu.unicrm.repository;
import kz.edu.unicrm.domain.Manager;
import kz.edu.unicrm.domain.Student;
import kz.edu.unicrm.domain.Teacher;
import java.util.ArrayList;
import java.util.List;
public class UserDataWrapper {
    private List<Student> students = new ArrayList<>();
    private List<Teacher> teachers = new ArrayList<>();
    private List<Manager> managers = new ArrayList<>();
    public List<Student> getStudents() {
        if (students == null) {
            students = new ArrayList<>();
        }
        return students;
    }
    public void setStudents(List<Student> students) {
        this.students = students;
    }
    public List<Teacher> getTeachers() {
        if (teachers == null) {
            teachers = new ArrayList<>();
        }
        return teachers;
    }
    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
    public List<Manager> getManagers() {
        if (managers == null) {
            managers = new ArrayList<>();
        }
        return managers;
    }
    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }
}
