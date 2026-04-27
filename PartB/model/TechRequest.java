package PartB.model;

import PartB.enums.RequestStatus;

public class TechRequest {

    private int requestId;
    private String description;
    private RequestStatus status;

    public TechRequest() {
        this.status = RequestStatus.NEW;
    }

    public TechRequest(int requestId, String description, RequestStatus status) {
        this.requestId = requestId;
        this.description = description;
        this.status = status;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TechRequest{" +
                "requestId=" + requestId +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}