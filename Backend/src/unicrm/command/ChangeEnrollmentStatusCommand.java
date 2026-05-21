package unicrm.command;

import unicrm.domain.Enrollment;
import unicrm.domain.EnrollmentStatus;
import unicrm.domain.Manager;
import unicrm.domain.User;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.repository.EnrollmentRepository;
import unicrm.service.EnrollmentService;
import unicrm.session.UserSession;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        List<Enrollment> enrollments = enrollmentRepository.findAll().stream()
                .filter(e -> e.getStatus() == EnrollmentStatus.PENDING)
                .toList();

        if (enrollments.isEmpty()) {
            System.out.println(localization.get(LocalizationKey.NO_PENDING_ENROLLMENTS));
            return;
        }

        System.out.println(localization.get(LocalizationKey.SELECT_ENROLLMENT));
        for (int i = 0; i < enrollments.size(); i++) {
            Enrollment e = enrollments.get(i);
            System.out.println(localization.format(LocalizationKey.ENROLLMENT_ITEM, i + 1, e.getStudent().getUsername(), e.getCourseOffering().getCourse().getName()));
        }

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(localization.get(LocalizationKey.INVALID_CHOICE));
            return;
        }

        if (choice < 1 || choice > enrollments.size()) {
            System.out.println(localization.get(LocalizationKey.INVALID_CHOICE));
            return;
        }

        Enrollment enrollment = enrollments.get(choice - 1);

        System.out.println(localization.get(LocalizationKey.CHOOSE_STATUS));
        System.out.println(localization.get(LocalizationKey.STATUS_APPROVED_CHOICE));
        System.out.println(localization.get(LocalizationKey.STATUS_REJECTED_CHOICE));
        String statusChoice = scanner.nextLine();

        if (statusChoice.equals("1")) {
            enrollmentService.approveEnrollment(enrollment);
            System.out.println(localization.get(LocalizationKey.ENROLLMENT_APPROVED));
        } else if (statusChoice.equals("2")) {
            enrollmentService.rejectEnrollment(enrollment);
            System.out.println(localization.get(LocalizationKey.ENROLLMENT_REJECTED));
        } else {
            System.out.println(localization.get(LocalizationKey.INVALID_CHOICE));
        }
    }
}
