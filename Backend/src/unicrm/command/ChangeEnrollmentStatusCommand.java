package unicrm.command;
import unicrm.domain.Enrollment;
import unicrm.domain.Manager;
import unicrm.domain.User;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.repository.EnrollmentRepository;
import unicrm.service.EnrollmentService;
import unicrm.session.UserSession;
import java.util.List;
import java.util.Scanner;

public class ChangeEnrollmentStatusCommand {

    private final EnrollmentService enrollmentService;
    private final EnrollmentRepository enrollmentRepository;
    private final UserSession userSession;
    private final Scanner scanner;
    private final LocalizationService localization = LocalizationService.getInstance();

    public ChangeEnrollmentStatusCommand(
            EnrollmentService enrollmentService,
            EnrollmentRepository enrollmentRepository,
            UserSession userSession,
            Scanner scanner
    ) {
        this.enrollmentService = enrollmentService;
        this.enrollmentRepository = enrollmentRepository;
        this.userSession = userSession;
        this.scanner = scanner;
    }

    public void execute() {
        User currentUser = userSession.getCurrentUser();
        if (!(currentUser instanceof Manager)) {
            System.out.println(localization.get(LocalizationKey.ACCESS_DENIED));
            return;
        }
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        if (enrollments.isEmpty()) {
            System.out.println(localization.get(LocalizationKey.NO_ENROLLMENTS_FOUND));
            return;
        }

        Enrollment enrollment = enrollments.getFirst();
        System.out.println(localization.get(LocalizationKey.CHOOSE_STATUS));
        System.out.println("1. APPROVED");
        System.out.println("2. REJECTED");
        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            enrollmentService.approveEnrollment(enrollment);
            System.out.println(localization.get(LocalizationKey.ENROLLMENT_APPROVED));
        } else if (choice.equals("2")) {
            enrollmentService.rejectEnrollment(enrollment);
            System.out.println(localization.get(LocalizationKey.ENROLLMENT_REJECTED));
        }
    }
}
