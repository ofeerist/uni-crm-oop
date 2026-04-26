package PartB.model;

import java.util.Date;

public class Message {

    private User sender;
    private User receiver;
    private String content;
    private Date date;

    public Message() {
        this.date = new Date();
    }

    public Message(User sender, User receiver, String content, Date date) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.date = date;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender.getFirstName() +
                ", receiver=" + receiver.getFirstName() +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }
}
