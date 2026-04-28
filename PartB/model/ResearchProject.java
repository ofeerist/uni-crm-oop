package PartB.model;

import java.util.Date;
import java.util.Objects;

public class ResearchProject {

    private final String id;
    private String topic;
    private Date startDate;
    private Date endDate;

    public ResearchProject(String id, String topic, Date startDate, Date endDate) {
        this.id = id;
        this.topic = topic;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResearchProject)) return false;
        ResearchProject that = (ResearchProject) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ResearchProject{" +
                "id='" + id + '\'' +
                ", topic='" + topic + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}