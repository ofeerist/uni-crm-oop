package PartB.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import PartB.exceptions.NotResearcherException;

public class ResearchProject {

    private final String id;
    private String topic;
    private Date startDate;
    private Date endDate;
    private final List<Researcher> participants;  
    private final List<ResearchPaper> papers;      

    public ResearchProject(String id, String topic, Date startDate, Date endDate) {
        this.id = id;
        this.topic = topic;
        this.startDate = startDate;
        this.endDate = endDate;
        this.participants = new ArrayList<>();
        this.papers = new ArrayList<>();
    }

    
    public void addParticipant(User user) throws NotResearcherException {
        if (!(user instanceof Researcher)) {
            throw new NotResearcherException(user.getFullName());
        }
        participants.add((Researcher) user);
    }

    public void addPaper(ResearchPaper paper) {
        papers.add(paper);
    }

    public List<Researcher> getParticipants() { return participants; }
    public List<ResearchPaper> getPapers()    { return papers; }

    public String getId()                      { return id; }
    public String getTopic()                   { return topic; }
    public void   setTopic(String topic)       { this.topic = topic; }
    public Date   getStartDate()               { return startDate; }
    public void   setStartDate(Date d)         { this.startDate = d; }
    public Date   getEndDate()                 { return endDate; }
    public void   setEndDate(Date d)           { this.endDate = d; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResearchProject)) return false;
        return Objects.equals(id, ((ResearchProject) o).id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "ResearchProject{id='" + id + "', topic='" + topic
                + "', participants=" + participants.size()
                + ", papers=" + papers.size() + "}";
    }
}