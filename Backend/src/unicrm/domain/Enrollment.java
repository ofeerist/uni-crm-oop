package unicrm.domain;

public class Enrollment {
    private Student student;
    private CourseOffering courseOffering;
    private EnrollmentStatus status;
    private MarkData grades;

    public Enrollment(Student student, CourseOffering courseOffering) {
        this.student = student;
        this.courseOffering = courseOffering;
        this.status = EnrollmentStatus.PENDING;
        this.grades = new MarkData(0, 0, 0);
    }

    public void approve() {
        this.status = EnrollmentStatus.APPROVED;
    }

    public void reject() {
        this.status = EnrollmentStatus.REJECTED;
    }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public CourseOffering getCourseOffering() { return courseOffering; }
    public void setCourseOffering(CourseOffering courseOffering) { this.courseOffering = courseOffering; }
    public EnrollmentStatus getStatus() { return status; }
    public void setStatus(EnrollmentStatus status) { this.status = status; }
    public MarkData getGrades() { return grades; }
    public void setGrades(MarkData grades) { this.grades = grades; }
}