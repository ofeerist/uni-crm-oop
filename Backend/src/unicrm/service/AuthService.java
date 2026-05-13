package unicrm.service;
import unicrm.domain.User;
import unicrm.repository.UserRepository;
import unicrm.session.UserSession;
public class AuthService {
    private final UserRepository userRepository;
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void login(String username, String password) {
        for (User user : userRepository.findAll()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                UserSession.getInstance().setCurrentUser(user);
                return;
            }
        }
    }
    public void logout() {
        UserSession.getInstance().clearSession();
    }
}
