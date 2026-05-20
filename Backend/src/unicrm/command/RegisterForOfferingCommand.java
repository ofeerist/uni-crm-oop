package unicrm.command;
import unicrm.domain.CourseOffering;
import unicrm.domain.Student;
import unicrm.domain.User;
import unicrm.repository.CourseOfferingRepository;
import unicrm.service.EnrollmentService;
import unicrm.session.UserSession;
import java.util.List;

public class RegisterForOfferingCommand {

    private final EnrollmentService enrollmentService;
    private final CourseOfferingRepository offeringRepository;
    private final UserSession userSession;

    public RegisterForOfferingCommand(
            EnrollmentService enrollmentService,
            CourseOfferingRepository offeringRepository,
            UserSession userSession
    ) {
        this.enrollmentService = enrollmentService;
        this.offeringRepository = offeringRepository;
        this.userSession = userSession;
    }

    public void execute() {
        User currentUser = userSession.getCurrentUser();
        if (!(currentUser instanceof Student student)) {
            return;
        }
        List<CourseOffering> offerings = offeringRepository.findAll();
        if (offerings.isEmpty()) {
            System.out.println("No course offerings found.");
            return;
        }

        CourseOffering offering = offerings.getFirst();
        enrollmentService.registerStudent(student, offering);
        System.out.println("Registration request created. Status: PENDING");
    }
}
