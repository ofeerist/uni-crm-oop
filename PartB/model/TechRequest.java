package PartB.model;

import java.util.Date;
import java.util.Objects;

import PartB.enums.RequestStatus;

public class TechRequest {

    private final String id;
    private String description;
    private RequestStatus status;
    private final User author;
    private TechSupporter executor;
    private final Date creationDate;

    public TechRequest(String id, String description, RequestStatus status,
                       User author, TechSupporter executor, Date creationDate) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.author = author;
        this.executor = executor;
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public User getAuthor() {
        return author;
    }

    public TechSupporter getExecutor() {
        return executor;
    }

    public void setExecutor(TechSupporter executor) {
        this.executor = executor;
    }

    public Date getCreationDate() {
        return creationDate;
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
                ", author=" + author.getFullName() +
                ", executor=" + (executor != null ? executor.getFullName() : "not assigned") +
                ", creationDate=" + creationDate +
                '}';
    }
}