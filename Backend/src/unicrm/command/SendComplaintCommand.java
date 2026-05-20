package unicrm.command;
import unicrm.domain.Student;
import unicrm.domain.Teacher;
import unicrm.domain.UrgencyLevel;
import unicrm.domain.User;
import unicrm.repository.UserRepository;
import unicrm.service.CommunicationService;
import unicrm.session.UserSession;
import java.util.List;

public class SendComplaintCommand {

    private final CommunicationService communicationService;
    private final UserRepository userRepo;
    private final UserSession userSession;

    public SendComplaintCommand(
            CommunicationService communicationService,
            UserRepository userRepo,
            UserSession userSession
    ) {
        this.communicationService = communicationService;
        this.userRepo = userRepo;
        this.userSession = userSession;
    }

    public void execute() {
        User currentUser = userSession.getEffectiveUser();

        if (currentUser instanceof Teacher teacher) {
            List<User> users = userRepo.findByRole("Student");

            if (!users.isEmpty()) {
                Student student = (Student) users.getFirst();

                communicationService.sendComplaint(
                        teacher,
                        student,
                        UrgencyLevel.HIGH,
                        "Violation of rules"
                );
            }
        }
    }
}
