package PartB.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TechSupporter extends Employee {

    private int supportId;
    private List<TechRequest> requests;

    public TechSupporter() {
        super();
        this.requests = new ArrayList<>();
    }

    public TechSupporter(int userId, String firstName, String lastName, String password,
                         String email, String phoneNumber, Date registrationDate,
                         int employeeId, String position, int supportId) {

        super(userId, firstName, lastName, password, email, phoneNumber,
              registrationDate, employeeId, position);

        this.supportId = supportId;
        this.requests = new ArrayList<>();
    }

    public void viewRequests() {
        for (TechRequest request : requests) {
            System.out.println(request);
        }
    }

    public void acceptRequest(TechRequest request) {
        request.setStatus(PartB.enums.RequestStatus.ACCEPTED);
    }

    public void rejectRequest(TechRequest request) {
        request.setStatus(PartB.enums.RequestStatus.REJECTED);
    }

    public void completeRequest(TechRequest request) {
        request.setStatus(PartB.enums.RequestStatus.DONE);
    }

    public void addRequest(TechRequest request) {
        requests.add(request);
    }

    public int getSupportId() {
        return supportId;
    }

    public void setSupportId(int supportId) {
        this.supportId = supportId;
    }

    public List<TechRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<TechRequest> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return "TechSupporter{" +
                "supportId=" + supportId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}