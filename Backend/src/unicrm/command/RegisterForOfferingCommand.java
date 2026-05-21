package unicrm.command;

import unicrm.domain.CourseOffering;
import unicrm.domain.Student;
import unicrm.domain.User;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.repository.CourseOfferingRepository;
import unicrm.service.EnrollmentService;
import unicrm.session.UserSession;

import java.util.List;
import java.util.Scanner;

public class RegisterForOfferingCommand {

    private final EnrollmentService enrollmentService;
    private final CourseOfferingRepository offeringRepository;
    private final UserSession userSession;
    private final Scanner scanner;
    private final LocalizationService localization = LocalizationService.getInstance();

    public RegisterForOfferingCommand(
            EnrollmentService enrollmentService,
            CourseOfferingRepository offeringRepository,
            UserSession userSession,
            Scanner scanner
    ) {
        this.enrollmentService = enrollmentService;
        this.offeringRepository = offeringRepository;
        this.userSession = userSession;
        this.scanner = scanner;
    }

    public void execute() {
        User currentUser = userSession.getCurrentUser();
        if (!(currentUser instanceof Student student)) {
            return;
        }

        List<CourseOffering> offerings = offeringRepository.findAll();
        if (offerings.isEmpty()) {
            System.out.println(localization.get(LocalizationKey.NO_OFFERINGS_FOUND));
            return;
        }

        System.out.println(localization.get(LocalizationKey.SELECT_OFFERING_HEADER));
        for (int i = 0; i < offerings.size(); i++) {
            CourseOffering o = offerings.get(i);
            String instructor = o.getInstructor() != null ? o.getInstructor().getUsername() : "—";
            String semester = o.getSemester() != null
                    ? o.getSemester().getSeason() + " " + o.getSemester().getYear()
                    : "—";
            System.out.println((i + 1) + ". " + o.getCourse().getName()
                    + " (" + instructor + ") — " + semester);
        }
        System.out.print(localization.get(LocalizationKey.MENU_CHOICE));

        int idx;
        try {
            idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
        } catch (NumberFormatException e) {
            System.out.println(localization.get(LocalizationKey.INVALID_CHOICE));
            return;
        }
        if (idx < 0 || idx >= offerings.size()) {
            System.out.println(localization.get(LocalizationKey.INVALID_CHOICE));
            return;
        }

        CourseOffering offering = offerings.get(idx);
        enrollmentService.registerStudent(student, offering);
        System.out.println(localization.get(LocalizationKey.ENROLLMENT_CREATED_PENDING));
    }
}
