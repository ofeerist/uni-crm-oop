package unicrm.domain;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public abstract class User implements Comparable<User> {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Language preferredLanguage;
    private boolean isResearcher = false;
    private transient List<UniversityJournal> journalSubscriptions = new ArrayList<>();
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Language getPreferredLanguage() {
        return preferredLanguage;
    }
    public void setPreferredLanguage(Language preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }
    public boolean isResearcher() {
        return isResearcher;
    }
    public void setResearcher(boolean researcher) {
        isResearcher = researcher;
    }
    public List<UniversityJournal> getJournalSubscriptions() {
        if (journalSubscriptions == null) {
            journalSubscriptions = new ArrayList<>();
        }
        return journalSubscriptions;
    }
    public void setJournalSubscriptions(List<UniversityJournal> journalSubscriptions) {
        this.journalSubscriptions = journalSubscriptions;
    }
    @Override
    public int compareTo(User other) {
        if (username == null && other.username == null) {
            return 0;
        }
        if (username == null) {
            return -1;
        }
        if (other.username == null) {
            return 1;
        }
        return username.compareTo(other.username);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public String toString() {
        return "User{id='" + id + "', username='" + username + "'}";
    }
}
