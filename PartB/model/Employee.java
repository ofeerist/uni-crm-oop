package PartB.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Employee extends User {

    protected int employeeId;
    protected String position;
    protected List<Message> messages;

    public Employee() {
        super();
        this.messages = new ArrayList<>();
    }

    public Employee(int userId, String firstName, String lastName, String password,
                    String email, String phoneNumber, Date registrationDate,
                    int employeeId, String position) {
        super(userId, firstName, lastName, password, email, phoneNumber, registrationDate);
        this.employeeId = employeeId;
        this.position = position;
        this.messages = new ArrayList<>();
    }

    public void sendMessage(Message message) {
        messages.add(message);
    }

    public void receiveMessage(Message message) {
        messages.add(message);
    }

    public void viewMessages() {
        for (Message message : messages) {
            System.out.println(message);
        }
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", position='" + position + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}