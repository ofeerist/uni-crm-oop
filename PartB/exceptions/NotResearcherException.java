package PartB.exceptions;

public class NotResearcherException extends Exception {

    private final String userName;

    public NotResearcherException(String userName) {
        super("User '" + userName + "' is not a Researcher and cannot join a ResearchProject.");
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}