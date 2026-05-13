package unicrm.command;
import unicrm.domain.Manager;
import unicrm.domain.User;
import unicrm.service.CourseRegistrationService;
import unicrm.session.UserSession;
public class ApproveStudentRegistrationCommand {
    private final CourseRegistrationService courseRegistrationService;
    private final UserSession userSession;
    public ApproveStudentRegistrationCommand(
            CourseRegistrationService courseRegistrationService,
            UserSession userSession
    ) {
        this.courseRegistrationService = courseRegistrationService;
        this.userSession = userSession;
    }
    public void execute() {
        User currentUser = userSession.getCurrentUser();
        if (currentUser instanceof Manager) {
            System.out.println("Manager executed bulk approve command.");
        }
    }
}
