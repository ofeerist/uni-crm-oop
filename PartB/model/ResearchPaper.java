package PartB.model;

import java.util.Date;

public class ResearchPaper {

    private String title;
    private int citations;
    private int pages;
    private String doi;
    private Date datePublished;

    public ResearchPaper() {
        this.datePublished = new Date();
    }

    public ResearchPaper(String title, int citations, int pages,
                         String doi, Date datePublished) {
        this.title = title;
        this.citations = citations;
        this.pages = pages;
        this.doi = doi;
        this.datePublished = datePublished;
    }

    public String getCitation(String format) {
        return title + " (" + format + ")";
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

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    @Override
    public String toString() {
        return "ResearchPaper{" +
                "title='" + title + '\'' +
                ", citations=" + citations +
                ", pages=" + pages +
                ", doi='" + doi + '\'' +
                ", datePublished=" + datePublished +
                '}';
    }
}