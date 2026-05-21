package unicrm.command;

import unicrm.domain.Enrollment;
import unicrm.domain.EnrollmentStatus;
import unicrm.domain.Lesson;
import unicrm.domain.Student;
import unicrm.domain.User;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.repository.EnrollmentRepository;
import unicrm.session.UserSession;

import java.util.List;

public class ViewScheduleCommand {

    private final EnrollmentRepository enrollmentRepository;
    private final UserSession userSession;
    private final LocalizationService localization = LocalizationService.getInstance();

    public ViewScheduleCommand(EnrollmentRepository enrollmentRepository, UserSession userSession) {
        this.enrollmentRepository = enrollmentRepository;
        this.userSession = userSession;
    }

    public void execute() {
        User currentUser = userSession.getCurrentUser();
        if (!(currentUser instanceof Student student)) {
            return;
        }

        List<Enrollment> enrollments = enrollmentRepository.findAll().stream()
                .filter(e -> e.getStudent() != null
                        && e.getStudent().getId().equals(student.getId())
                        && e.getStatus() != EnrollmentStatus.REJECTED)
                .toList();

        if (enrollments.isEmpty()) {
            System.out.println(localization.get(LocalizationKey.NO_SCHEDULE_FOUND));
            return;
        }

        for (Enrollment enrollment : enrollments) {
            var offering = enrollment.getCourseOffering();
            String courseName = offering.getCourse() != null ? offering.getCourse().getName() : "—";
            String instructor = offering.getInstructor() != null ? offering.getInstructor().getUsername() : "—";
            String semester = offering.getSemester() != null
                    ? offering.getSemester().getSeason() + " " + offering.getSemester().getYear()
                    : "—";

            System.out.println(localization.format(
                    LocalizationKey.OFFERING_SCHEDULE_HEADER, courseName, instructor, semester));

            List<Lesson> lessons = offering.getLessons();
            if (lessons == null || lessons.isEmpty()) {
                System.out.println("  " + localization.get(LocalizationKey.NO_SCHEDULE_FOUND));
            } else {
                for (Lesson lesson : lessons) {
                    System.out.println("  " + localization.format(
                            LocalizationKey.LESSON_FORMAT,
                            lesson.getType(),
                            lesson.getTimeSlot(),
                            lesson.getRoom() != null ? lesson.getRoom().getName() : "—"));
                }
            }
        }
    }
}
