package PartB.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import PartB.enums.Format;

public class ResearchPaper implements Cloneable, Comparable<ResearchPaper> {

    private final String id;
    private String title;
    private int citations;
    private int pages;
    private String doi;
    private Date publicationDate;
    private List<String> authors;   // NEW: list of author names
    private String journalName;     // NEW: journal / conference name

    public ResearchPaper(String id, String title, int citations,
                         int pages, String doi, Date publicationDate) {
        this.id = id;
        this.title = title;
        this.citations = citations;
        this.pages = pages;
        this.doi = doi;
        this.publicationDate = publicationDate;
        this.authors = new ArrayList<>();
        this.journalName = "";
    }

    public ResearchPaper(String id, String title, int citations, int pages,
                         String doi, Date publicationDate,
                         List<String> authors, String journalName) {
        this(id, title, citations, pages, doi, publicationDate);
        this.authors = new ArrayList<>(authors);
        this.journalName = journalName;
    }

    public String getId()                        { return id; }
    public String getTitle()                     { return title; }
    public void   setTitle(String title)         { this.title = title; }
    public int    getCitations()                 { return citations; }
    public void   setCitations(int citations)    { this.citations = citations; }
    public int    getPages()                     { return pages; }
    public void   setPages(int pages)            { this.pages = pages; }
    public String getDoi()                       { return doi; }
    public void   setDoi(String doi)             { this.doi = doi; }
    public Date   getPublicationDate()           { return publicationDate; }
    public void   setPublicationDate(Date d)     { this.publicationDate = d; }
    public List<String> getAuthors()             { return authors; }
    public void   setAuthors(List<String> a)     { this.authors = a; }
    public String getJournalName()               { return journalName; }
    public void   setJournalName(String j)       { this.journalName = j; }


    public String getCitation() {
        return getCitation(Format.PLAIN_TEXT);
    }

    
    public String getCitation(Format f) {
        String authorsStr = authors.isEmpty() ? "Unknown" : String.join(", ", authors);
        int year = publicationDate != null ? (publicationDate.getYear() + 1900) : 0;

        switch (f) {
            case BIBTEX:
                String key = (authors.isEmpty() ? "unknown" : authors.get(0).split(" ")[0].toLowerCase())
                        + year;
                return "@article{" + key + ",\n"
                        + "  author  = {" + authorsStr + "},\n"
                        + "  title   = {" + title + "},\n"
                        + "  journal = {" + journalName + "},\n"
                        + "  year    = {" + year + "},\n"
                        + "  doi     = {" + doi + "}\n"
                        + "}";
            case PLAIN_TEXT:
            default:
                return authorsStr + " (" + year + "). "
                        + title + ". " + journalName + ". DOI: " + doi;
        }
    }



    @Override
    public int compareTo(ResearchPaper other) {
        return Integer.compare(other.citations, this.citations);
    }

    

    @Override
    public ResearchPaper clone() {
        try {
            ResearchPaper copy = (ResearchPaper) super.clone();
            copy.publicationDate = (Date) publicationDate.clone();
            copy.authors = new ArrayList<>(this.authors);
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResearchPaper)) return false;
        return Objects.equals(id, ((ResearchPaper) o).id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "ResearchPaper{id='" + id + "', title='" + title
                + "', citations=" + citations + ", doi='" + doi + "'}";
    }
}
