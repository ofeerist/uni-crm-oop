package PartB.model;

import java.util.Date;
import java.util.Objects;

public class ResearchPaper implements Cloneable {

    private final String id;
    private String title;
    private int citations;
    private int pages;
    private String doi;
    private Date publicationDate;

    public ResearchPaper(String id, String title, int citations,
                         int pages, String doi, Date publicationDate) {
        this.id = id;
        this.title = title;
        this.citations = citations;
        this.pages = pages;
        this.doi = doi;
        this.publicationDate = publicationDate;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCitations() {
        return citations;
    }

    public void setCitations(int citations) {
        this.citations = citations;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getCitation() {
        return title + ", DOI: " + doi;
    }

    @Override
    public ResearchPaper clone() {
        try {
            ResearchPaper copy = (ResearchPaper) super.clone();
            copy.publicationDate = (Date) publicationDate.clone();
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResearchPaper)) return false;
        ResearchPaper that = (ResearchPaper) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ResearchPaper{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", citations=" + citations +
                ", doi='" + doi + '\'' +
                '}';
    }
}