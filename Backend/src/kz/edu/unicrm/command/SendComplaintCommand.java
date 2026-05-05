package kz.edu.unicrm.command;
import kz.edu.unicrm.domain.Student;
import kz.edu.unicrm.domain.Teacher;
import kz.edu.unicrm.domain.UrgencyLevel;
import kz.edu.unicrm.domain.User;
import kz.edu.unicrm.repository.UserRepository;
import kz.edu.unicrm.service.CommunicationService;
import kz.edu.unicrm.session.UserSession;
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
        User currentUser = userSession.getCurrentUser();

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