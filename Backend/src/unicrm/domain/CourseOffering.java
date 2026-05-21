package unicrm.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CourseOffering {
    private String id;
    private Course course;
    private AcademicSemester semester;
    private Teacher instructor;
    private int capacity;
    private List<Lesson> lessons;

    public CourseOffering() {
        this.id = UUID.randomUUID().toString();
        this.lessons = new ArrayList<>();
    }

    public CourseOffering(Course course, AcademicSemester semester, Teacher instructor, int capacity) {
        this.id = UUID.randomUUID().toString();
        this.course = course;
        this.semester = semester;
        this.instructor = instructor;
        this.capacity = capacity;
        this.lessons = new ArrayList<>();
    }

    public void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
    public AcademicSemester getSemester() { return semester; }
    public void setSemester(AcademicSemester semester) { this.semester = semester; }
    public Teacher getInstructor() { return instructor; }
    public void setInstructor(Teacher instructor) { this.instructor = instructor; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public List<Lesson> getLessons() { return lessons; }
    public void setLessons(List<Lesson> lessons) { this.lessons = lessons; }
}