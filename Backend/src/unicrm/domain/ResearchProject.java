package unicrm.domain;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ResearchProject {

    private String id;
    private String topic;
    private List<String> participantIds;
    private List<String> paperIds;

    public ResearchProject(String topic) {
        this.id = UUID.randomUUID().toString();
        this.topic = topic;
        this.participantIds = new ArrayList<>();
        this.paperIds = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<String> getParticipantIds() {
        return participantIds;
    }

    public List<String> getPaperIds() {
        return paperIds;
    }

    public void addParticipant(String userId) {
        participantIds.add(userId);
    }

    public void addPaper(String paperId) {
        paperIds.add(paperId);
    }

    @Override
    public String toString() {
        return topic;
    }
}