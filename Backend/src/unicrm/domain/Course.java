package unicrm.domain;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class Course implements Comparable<Course> {
    private String courseId;
    private String name;
    private int credits;
    private CourseCategory category;
    private transient List<Lesson> lessons = new ArrayList<>();
    private transient List<Mark> marks = new ArrayList<>();
    private transient List<Student> enrolledStudents = new ArrayList<>();
    private transient List<Teacher> assignedTeachers = new ArrayList<>();
    public String getCourseId() {
        return courseId;
    }
    public void setCourseId(String courseId) {
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
    public CourseCategory getCategory() {
        return category;
    }
    public void setCategory(CourseCategory category) {
        this.category = category;
    }
    public List<Lesson> getLessons() {
        if (lessons == null) {
            lessons = new ArrayList<>();
        }
        return lessons;
    }
    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
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
    public List<Student> getEnrolledStudents() {
        if (enrolledStudents == null) {
            enrolledStudents = new ArrayList<>();
        }
        return enrolledStudents;
    }
    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }
    public List<Teacher> getAssignedTeachers() {
        if (assignedTeachers == null) {
            assignedTeachers = new ArrayList<>();
        }
        return assignedTeachers;
    }
    public void setAssignedTeachers(List<Teacher> assignedTeachers) {
        this.assignedTeachers = assignedTeachers;
    }
    @Override
    public int compareTo(Course other) {
        return Integer.compare(credits, other.credits);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course course)) return false;
        return Objects.equals(courseId, course.courseId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }
    @Override
    public String toString() {
        return "Course{id='" + courseId + "', name='" + name + "', credits=" + credits + "}";
    }
}
