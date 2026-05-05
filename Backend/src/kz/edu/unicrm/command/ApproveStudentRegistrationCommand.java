package kz.edu.unicrm.command;
import kz.edu.unicrm.domain.Manager;
import kz.edu.unicrm.domain.User;
import kz.edu.unicrm.service.CourseRegistrationService;
import kz.edu.unicrm.session.UserSession;
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
