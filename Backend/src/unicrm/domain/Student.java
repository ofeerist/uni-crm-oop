package unicrm.domain;
import java.util.ArrayList;
import java.util.List;
public class Student extends User {
    private String major;
    private int yearOfStudy;
    private int currentCredits;
    private int failCount;
    private transient List<Course> registeredCourses = new ArrayList<>();
    private transient List<Mark> marks = new ArrayList<>();
    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }
    public int getYearOfStudy() {
        return yearOfStudy;
    }
    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }
    public int getCurrentCredits() {
        return currentCredits;
    }
    public void setCurrentCredits(int currentCredits) {
        this.currentCredits = currentCredits;
    }
    public int getFailCount() {
        return failCount;
    }
    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }
    public List<Course> getRegisteredCourses() {
        if (registeredCourses == null) {
            registeredCourses = new ArrayList<>();
        }
        return registeredCourses;
    }
    public void setRegisteredCourses(List<Course> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }
    public List<Mark> getMarks() {
        if (marks == null) {
            marks = new ArrayList<>();
        }
        return marks;
    }
    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }
    @Override
    public String toString() {
        return "Student{username='" + getUsername() + "', major='" + major + "', yearOfStudy=" + yearOfStudy + "}";
    }
}
