package unicrm.session;
import unicrm.domain.ResearcherDecorator;
import unicrm.domain.User;
public class UserSession {
    private static UserSession instance;
    private User currentUser;
    private UserSession() {
    }
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }
    public User getCurrentUser() {
        return currentUser;
    }

    public User getEffectiveUser() {
        if (currentUser instanceof ResearcherDecorator rd) {
            return rd.getBaseUser();
        }
        return currentUser;
    }
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
    public void clearSession() {
        this.currentUser = null;
    }
}
