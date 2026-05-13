package unicrm.domain;
import java.util.ArrayList;
import java.util.List;
public abstract class Employee extends User {
    private transient List<Message> sentMessages = new ArrayList<>();
    private transient List<Message> receivedMessages = new ArrayList<>();
    public List<Message> getSentMessages() {
        if (sentMessages == null) {
            sentMessages = new ArrayList<>();
        }
        return sentMessages;
    }
    public void setSentMessages(List<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }
    public List<Message> getReceivedMessages() {
        if (receivedMessages == null) {
            receivedMessages = new ArrayList<>();
        }
        return receivedMessages;
    }
    public void setReceivedMessages(List<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }
}
