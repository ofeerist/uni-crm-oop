package unicrm.command;

import unicrm.domain.CourseOffering;
import unicrm.domain.Enrollment;
import unicrm.domain.EnrollmentStatus;
import unicrm.domain.MarkData;
import unicrm.domain.Student;
import unicrm.domain.Teacher;
import unicrm.domain.User;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.repository.CourseOfferingRepository;
import unicrm.repository.EnrollmentRepository;
import unicrm.service.AcademicService;
import unicrm.session.UserSession;

import java.util.List;
import java.util.Scanner;

public class PutMarkCommand {

    private final AcademicService academicService;
    private final CourseOfferingRepository offeringRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final UserSession userSession;
    private final Scanner scanner;
    private final LocalizationService localization = LocalizationService.getInstance();

    public PutMarkCommand(
            AcademicService academicService,
            CourseOfferingRepository offeringRepository,
            EnrollmentRepository enrollmentRepository,
            UserSession userSession,
            Scanner scanner
    ) {
        this.academicService = academicService;
        this.offeringRepository = offeringRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.userSession = userSession;
        this.scanner = scanner;
    }

    public void execute() {
        User currentUser = userSession.getEffectiveUser();
        if (!(currentUser instanceof Teacher teacher)) {
            return;
        }

        // Step 1: select offering taught by this teacher
        List<CourseOffering> myOfferings = offeringRepository.findAll().stream()
                .filter(o -> o.getInstructor() != null
                        && o.getInstructor().getId().equals(teacher.getId()))
                .toList();

        if (myOfferings.isEmpty()) {
            System.out.println(localization.get(LocalizationKey.NO_COURSES_ASSIGNED));
            return;
        }

        System.out.println(localization.get(LocalizationKey.SELECT_OFFERING_HEADER));
        for (int i = 0; i < myOfferings.size(); i++) {
            CourseOffering o = myOfferings.get(i);
            System.out.println((i + 1) + ". " + o.getCourse().getName()
                    + " — " + o.getSemester().getSeason() + " " + o.getSemester().getYear());
        }
        System.out.print(localization.get(LocalizationKey.MENU_CHOICE));

        int offeringIdx = parseIndex(scanner.nextLine(), myOfferings.size());
        if (offeringIdx < 0) {
            System.out.println(localization.get(LocalizationKey.INVALID_CHOICE));
            return;
        }
        CourseOffering offering = myOfferings.get(offeringIdx);

        // Step 2: select student from approved enrollments for that offering
        List<Enrollment> approved = enrollmentRepository.findAll().stream()
                .filter(e -> e.getStatus() == EnrollmentStatus.APPROVED
                        && e.getCourseOffering() != null
                        && offeringKey(e.getCourseOffering()).equals(offeringKey(offering)))
                .toList();

        if (approved.isEmpty()) {
            System.out.println(localization.get(LocalizationKey.NO_ENROLLED_STUDENTS));
            return;
        }

        System.out.println(localization.get(LocalizationKey.COMPLAINT_SELECT_STUDENT));
        for (int i = 0; i < approved.size(); i++) {
            System.out.println((i + 1) + ". " + approved.get(i).getStudent().getUsername());
        }
        System.out.print(localization.get(LocalizationKey.ENTER_STUDENT_NUMBER));

        int studentIdx = parseIndex(scanner.nextLine(), approved.size());
        if (studentIdx < 0) {
            System.out.println(localization.get(LocalizationKey.INVALID_CHOICE));
            return;
        }
        Student student = approved.get(studentIdx).getStudent();

        // Step 3: enter mark components
        double first = readDouble(LocalizationKey.ENTER_FIRST_ATTESTATION);
        if (first < 0) return;
        double second = readDouble(LocalizationKey.ENTER_SECOND_ATTESTATION);
        if (second < 0) return;
        double finalExam = readDouble(LocalizationKey.ENTER_FINAL_EXAM);
        if (finalExam < 0) return;

        academicService.putMark(teacher, student, offering.getCourse(), new MarkData(first, second, finalExam));
        System.out.println(localization.get(LocalizationKey.MARK_SAVED));
    }

    private double readDouble(LocalizationKey promptKey) {
        System.out.print(localization.get(promptKey));
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(localization.get(LocalizationKey.INVALID_CHOICE));
            return -1;
        }
    }

    private int parseIndex(String input, int max) {
        try {
            int idx = Integer.parseInt(input.trim()) - 1;
            return (idx >= 0 && idx < max) ? idx : -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private String offeringKey(CourseOffering o) {
        return o.getCourse().getCourseId()
                + "-" + o.getSemester().getSeason()
                + "-" + o.getSemester().getYear();
    }
}
