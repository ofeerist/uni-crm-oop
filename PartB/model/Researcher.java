package PartB.model;

import java.util.ArrayList;
import java.util.List;

public class Researcher extends UserDecorator {

    private List<ResearchPaper> papers;
    private List<ResearchProject> projects;

    public Researcher(User user) {
        super(user);
        this.papers = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    public int calculateHIndex() {
        return papers.size();
    }

    public void joinProject(ResearchProject project) {
        projects.add(project);
        project.addParticipant(this);
    }

    public void addPaper(ResearchPaper paper) {
        papers.add(paper);
    }

    public void printPapers() {
        for (ResearchPaper paper : papers) {
            System.out.println(paper);
        }
    }

    public List<ResearchPaper> getPapers() {
        return papers;
    }

    public void setPapers(List<ResearchPaper> papers) {
        this.papers = papers;
    }

    public List<ResearchProject> getProjects() {
        return projects;
    }

    public void setProjects(List<ResearchProject> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Researcher{" +
                "papers=" + papers.size() +
                ", projects=" + projects.size() +
                ", user=" + decoratedUser.getFirstName() +
                '}';
    }
}