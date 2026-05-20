package unicrm.domain;

public class ResearcherDecorator extends User {

    private final User baseUser;

    public ResearcherDecorator(User baseUser) {
        this.baseUser = baseUser;
        setId(baseUser.getId());
        setUsername(baseUser.getUsername());
        setPassword(baseUser.getPassword());
        setFirstName(baseUser.getFirstName());
        setLastName(baseUser.getLastName());
        setPreferredLanguage(baseUser.getPreferredLanguage());
        setResearcher(true);
        setJournalSubscriptions(baseUser.getJournalSubscriptions());
    }

    public User getBaseUser() {
        return baseUser;
    }

    @Override
    public String toString() {
        return "Researcher{baseUser=" + baseUser + "}";
    }
}
