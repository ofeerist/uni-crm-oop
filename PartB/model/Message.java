package PartB.model;

import java.util.Date;
import java.util.Objects;

public class Message {

    private final int messageId;
    private final User sender;
    private final User receiver;
    private String content;
    private final Date sentDate;

    public Message(int messageId, User sender, User receiver,
                   String content, Date sentDate) {
        this.messageId = messageId;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.sentDate = sentDate;
    }

    public int getMessageId() {
        return messageId;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSentDate() {
        return sentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return messageId == message.messageId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId);
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", sender=" + sender.getFullName() +
                ", receiver=" + receiver.getFullName() +
                ", content='" + content + '\'' +
                ", sentDate=" + sentDate +
                '}';
    }
}