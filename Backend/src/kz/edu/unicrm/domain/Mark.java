package kz.edu.unicrm.domain;
import java.util.Objects;
public class Mark {
    private String id;
    private double firstAttestation;
    private double secondAttestation;
    private double finalExam;
    private Student student;
    private Course course;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public double getFirstAttestation() {
        return firstAttestation;
    }
    public void setFirstAttestation(double firstAttestation) {
        this.firstAttestation = firstAttestation;
    }
    public double getSecondAttestation() {
        return secondAttestation;
    }
    public void setSecondAttestation(double secondAttestation) {
        this.secondAttestation = secondAttestation;
    }
    public double getFinalExam() {
        return finalExam;
    }
    public void setFinalExam(double finalExam) {
        this.finalExam = finalExam;
    }
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    public double getTotal() {
        return firstAttestation + secondAttestation + finalExam;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mark mark)) return false;
        return Objects.equals(id, mark.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public String toString() {
        return "Mark{id='" + id + "', total=" + getTotal() + "}";
    }
}
