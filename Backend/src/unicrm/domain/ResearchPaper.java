package unicrm.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ResearchPaper {
    private String id;
    private String title;
    private String paperAbstract;
    private String authorUsername;
    private String journalName;
    private List<String> citedPaperIds = new ArrayList<>();
    private int citations;

    public ResearchPaper() {
        this.id = UUID.randomUUID().toString();
    }

    public ResearchPaper(String title, String paperAbstract, String authorUsername) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.paperAbstract = paperAbstract;
        this.authorUsername = authorUsername;
        this.citations = 0;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPaperAbstract() { return paperAbstract; }
    public void setPaperAbstract(String paperAbstract) { this.paperAbstract = paperAbstract; }

    public String getAuthorUsername() { return authorUsername; }
    public void setAuthorUsername(String authorUsername) { this.authorUsername = authorUsername; }

    public String getJournalName() { return journalName; }
    public void setJournalName(String journalName) { this.journalName = journalName; }

    public List<String> getCitedPaperIds() {
        if (citedPaperIds == null) citedPaperIds = new ArrayList<>();
        return citedPaperIds;
    }
    public void setCitedPaperIds(List<String> citedPaperIds) { this.citedPaperIds = citedPaperIds; }

    public int getCitations() { return citations; }
    public void setCitations(int citations) { this.citations = citations; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResearchPaper p)) return false;
        return Objects.equals(id, p.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "ResearchPaper{title='" + title + "', journal='" + journalName
                + "', author='" + authorUsername + "', citations=" + citations + "}";
    }
}
