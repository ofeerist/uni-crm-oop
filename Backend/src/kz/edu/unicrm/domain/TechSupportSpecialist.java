package kz.edu.unicrm.domain;
import java.util.ArrayList;
import java.util.List;

public class TechSupportSpecialist extends Employee {

    private List<TechRequest> handledRequests = new ArrayList<>();

    public TechSupportSpecialist() {
    }

    public List<TechRequest> getHandledRequests() {
        if (handledRequests == null) {
            handledRequests = new ArrayList<>();
        }
        return handledRequests;
    }

    public void setHandledRequests(List<TechRequest> handledRequests) {
        this.handledRequests = handledRequests;
    }

    public void addHandledRequest(TechRequest request) {
        getHandledRequests().add(request);
    }

    @Override
    public String toString() {
        return "TechSupportSpecialist{" +
                "id='" + getId() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", handledRequests=" + getHandledRequests().size() +
                '}';
    }
}