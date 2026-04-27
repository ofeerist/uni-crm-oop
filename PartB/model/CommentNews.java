package PartB.model;

import java.util.Date;

public class CommentNews {

    private String content;
    private User author;
    private Date timestamp;
    private boolean pinned;

    public CommentNews() {
        this.timestamp = new Date();
        this.pinned = false;
    }

    public CommentNews(String content, User author, Date timestamp, boolean pinned) {
        this.content = content;
        this.author = author;
        this.timestamp = timestamp;
        this.pinned = pinned;
    }

    public void pinComment() {
        this.pinned = true;
    }

    public void editComment(String newContent) {
        this.content = newContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

    @Override
    public String toString() {
        return "CommentNews{" +
                "content='" + content + '\'' +
                ", author=" + author.getFirstName() +
                ", timestamp=" + timestamp +
                ", pinned=" + pinned +
                '}';
    }
}