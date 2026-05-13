package unicrm.domain;
import java.util.Date;
import java.util.Objects;

public class Message {

    private String id;
    private Employee sender;
    private Employee receiver;
    private String content;
    private Date timestamp;

    public Message() {
    }

    public String getId() {
        return id;
    }

    public Employee getSender() {
        return sender;
    }

    public Employee getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSender(Employee sender) {
        this.sender = sender;
    }

    public void setReceiver(Employee receiver) {
        this.receiver = receiver;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", sender=" + (sender != null ? sender.getUsername() : null) +
                ", receiver=" + (receiver != null ? receiver.getUsername() : null) +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}