package unicrm.domain;
import java.util.Date;
import java.util.Objects;

public class TechRequest {

    private String id;
    private String description;
    private RequestStatus status;
    private User author;
    private TechSupportSpecialist executor;
    private Date creationDate;

    public TechRequest() {
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public User getAuthor() {
        return author;
    }

    public TechSupportSpecialist getExecutor() {
        return executor;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setExecutor(TechSupportSpecialist executor) {
        this.executor = executor;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TechRequest)) return false;
        TechRequest that = (TechRequest) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TechRequest{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", author=" + (author != null ? author.getUsername() : null) +
                ", executor=" + (executor != null ? executor.getUsername() : null) +
                ", creationDate=" + creationDate +
                '}';
    }
}
