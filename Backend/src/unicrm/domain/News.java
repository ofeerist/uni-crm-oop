package unicrm.domain;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class News {
    private String id;
    private String title;
    private String text;
    private String authorUsername;
    private Date publishedAt;

    public News() {
        this.id = UUID.randomUUID().toString();
        this.publishedAt = new Date();
    }

    public News(String title, String text, String authorUsername) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.text = text;
        this.authorUsername = authorUsername;
        this.publishedAt = new Date();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getAuthorUsername() { return authorUsername; }
    public void setAuthorUsername(String authorUsername) { this.authorUsername = authorUsername; }

    public Date getPublishedAt() { return publishedAt; }
    public void setPublishedAt(Date publishedAt) { this.publishedAt = publishedAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News n)) return false;
        return Objects.equals(id, n.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "News{title='" + title + "', author='" + authorUsername + "'}";
    }
}
