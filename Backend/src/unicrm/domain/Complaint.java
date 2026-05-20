package unicrm.domain;
import java.util.Date;
import java.util.Objects;

public class Complaint {

    private String id;
    private String reason;
    private UrgencyLevel urgency;
    private Teacher author;
    private Student accusedStudent;
    private Date creationDate;

    public Complaint() {
    }

    public String getId() {
        return id;
    }

    public String getReason() {
        return reason;
    }

    public UrgencyLevel getUrgency() {
        return urgency;
    }

    public Teacher getAuthor() {
        return author;
    }

    public Student getAccusedStudent() {
        return accusedStudent;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setUrgency(UrgencyLevel urgency) {
        this.urgency = urgency;
    }

    public void setAuthor(Teacher author) {
        this.author = author;
    }

    public void setAccusedStudent(Student accusedStudent) {
        this.accusedStudent = accusedStudent;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Complaint)) return false;
        Complaint that = (Complaint) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "id='" + id + '\'' +
                ", reason='" + reason + '\'' +
                ", urgency=" + urgency +
                ", author=" + (author != null ? author.getUsername() : null) +
                ", accusedStudent=" + (accusedStudent != null ? accusedStudent.getUsername() : null) +
                ", creationDate=" + creationDate +
                '}';
    }
}
