package PartB.model;

import java.util.ArrayList;
import java.util.List;

public class UniversityJournal {

    private int journalId;
    private String name;
    private String topic;
    private List<ResearchPaper> papers;
    private List<User> subscribers;

    public UniversityJournal() {
        this.papers = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public UniversityJournal(int journalId, String name, String topic) {
        this.journalId = journalId;
        this.name = name;
        this.topic = topic;
        this.papers = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public void addResearchPaper(ResearchPaper paper) {
        papers.add(paper);
    }

    public void subscribe(User user) {
        subscribers.add(user);
    }

    public void notifySubscribers() {
        for (User user : subscribers) {
            System.out.println(user.getFirstName() +
                    " received notification from journal " + name);
        }
    }

    public int getJournalId() {
        return journalId;
    }

    public void setJournalId(int journalId) {
        this.journalId = journalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<ResearchPaper> getPapers() {
        return papers;
    }

    public void setPapers(List<ResearchPaper> papers) {
        this.papers = papers;
    }

    public List<User> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<User> subscribers) {
        this.subscribers = subscribers;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "journalId=" + journalId +
                ", name='" + name + '\'' +
                ", topic='" + topic + '\'' +
                ", papers=" + papers.size() +
                ", subscribers=" + subscribers.size() +
                '}';
    }
}