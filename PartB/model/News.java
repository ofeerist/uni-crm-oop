package PartB.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class News {

    private int newsId;
    private String title;
    private String content;
    private String topic;
    private Date date;
    private List<CommentNews> comments;

    public News() {
        this.date = new Date();
        this.comments = new ArrayList<>();
    }

    public News(int newsId, String title, String content, String topic, Date date) {
        this.newsId = newsId;
        this.title = title;
        this.content = content;
        this.topic = topic;
        this.date = date;
        this.comments = new ArrayList<>();
    }

    public void addComment(CommentNews comment) {
        comments.add(comment);
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<CommentNews> getComments() {
        return comments;
    }

    public void setComments(List<CommentNews> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", title='" + title + '\'' +
                ", topic='" + topic + '\'' +
                ", date=" + date +
                '}';
    }
}