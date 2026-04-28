package PartB.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Researcher {

    
    protected final User user;

    private final List<ResearchProject> projects;
    private final List<ResearchPaper>   papers;

    public Researcher(User user) {
        this.user     = user;
        this.projects = new ArrayList<>();
        this.papers   = new ArrayList<>();
    }


    public String getFullName()  { return user.getFullName(); }
    public String getEmail()     { return user.getEmail(); }
    public User   getUser()      { return user; }


    public void addProject(ResearchProject project) { projects.add(project); }
    public void addPaper(ResearchPaper paper)        { papers.add(paper); }

    public List<ResearchProject> getProjects() { return projects; }
    public List<ResearchPaper>   getPapers()   { return papers; }

   
    public int calculateHIndex() {
        List<Integer> citationCounts = new ArrayList<>();
        for (ResearchPaper p : papers) {
            citationCounts.add(p.getCitations());
        }
        Collections.sort(citationCounts, Collections.reverseOrder());

        int h = 0;
        for (int i = 0; i < citationCounts.size(); i++) {
            if (citationCounts.get(i) >= i + 1) {
                h = i + 1;
            } else {
                break;
            }
        }
        return h;
    }

    
    public void printPapers(Comparator<ResearchPaper> comparator) {
        List<ResearchPaper> sorted = new ArrayList<>(papers);
        sorted.sort(comparator);
        System.out.println("Papers of " + getFullName() + ":");
        for (ResearchPaper p : sorted) {
            System.out.println("  " + p);
        }
    }

    @Override
    public String toString() {
        return "Researcher{user=" + getFullName()
                + ", hIndex=" + calculateHIndex()
                + ", papers=" + papers.size() + "}";
    }
}