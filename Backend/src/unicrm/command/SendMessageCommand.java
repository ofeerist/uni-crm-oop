package unicrm.command;
import unicrm.domain.Employee;
import unicrm.domain.User;
import unicrm.repository.UserRepository;
import unicrm.service.CommunicationService;
import unicrm.session.UserSession;
import java.util.List;

public class SendMessageCommand {

    private final CommunicationService communicationService;
    private final UserRepository userRepo;
    private final UserSession userSession;

    public SendMessageCommand(
            CommunicationService communicationService,
            UserRepository userRepo,
            UserSession userSession
    ) {
        this.communicationService = communicationService;
        this.userRepo = userRepo;
        this.userSession = userSession;
    }

    public void execute() {
        User currentUser = userSession.getCurrentUser();
        if (!(currentUser instanceof Employee sender)) {
            System.out.println("Only employees can send messages.");
            return;
        }
        List<User> users = userRepo.findAll();
        for (User user : users) {
            if (user instanceof Employee receiver && receiver != sender) {
                communicationService.sendMessage(
                        sender,
                        receiver,
                        "Hello from system"
                );
                System.out.println("Message sent.");
                return;
            }
        }
        System.out.println("No employee receiver found.");
    }
}