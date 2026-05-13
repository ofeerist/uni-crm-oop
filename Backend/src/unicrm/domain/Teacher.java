package unicrm.domain;
import java.util.ArrayList;
import java.util.List;
public class Teacher extends Employee {
    private String position;
    private double averageRating;
    private TeacherType type;
    private transient List<Course> assignedCourses = new ArrayList<>();
    private transient List<Complaint> complaintsFiled = new ArrayList<>();
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public double getAverageRating() {
        return averageRating;
    }
    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
    public TeacherType getType() {
        return type;
    }
    public void setType(TeacherType type) {
        this.type = type;
    }
    public List<Course> getAssignedCourses() {
        if (assignedCourses == null) {
            assignedCourses = new ArrayList<>();
        }
        return assignedCourses;
    }
    public void setAssignedCourses(List<Course> assignedCourses) {
        this.assignedCourses = assignedCourses;
    }
    public List<Complaint> getComplaintsFiled() {
        if (complaintsFiled == null) {
            complaintsFiled = new ArrayList<>();
        }
        return complaintsFiled;
    }
    public void setComplaintsFiled(List<Complaint> complaintsFiled) {
        this.complaintsFiled = complaintsFiled;
    }
    @Override
    public String toString() {
        return "Teacher{username='" + getUsername() + "', position='" + position + "'}";
    }
}
