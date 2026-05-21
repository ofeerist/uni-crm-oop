package unicrm.command;

import unicrm.domain.CourseOffering;
import unicrm.domain.Lesson;
import unicrm.domain.Teacher;
import unicrm.domain.User;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.repository.CourseOfferingRepository;
import unicrm.session.UserSession;

import java.util.List;

public class ViewTeacherScheduleCommand {

    private final CourseOfferingRepository offeringRepository;
    private final UserSession userSession;
    private final LocalizationService localization = LocalizationService.getInstance();

    public ViewTeacherScheduleCommand(CourseOfferingRepository offeringRepository, UserSession userSession) {
        this.offeringRepository = offeringRepository;
        this.userSession = userSession;
    }

    public void execute() {
        User currentUser = userSession.getCurrentUser();
        if (!(currentUser instanceof Teacher teacher)) {
            return;
        }

        List<CourseOffering> myOfferings = offeringRepository.findAll().stream()
                .filter(o -> o.getInstructor() != null
                        && o.getInstructor().getId().equals(teacher.getId()))
                .toList();

        if (myOfferings.isEmpty()) {
            System.out.println(localization.get(LocalizationKey.NO_SCHEDULE_FOUND));
            return;
        }

        for (CourseOffering o : myOfferings) {
            String courseName = o.getCourse() != null ? o.getCourse().getName() : "—";
            String semester = o.getSemester() != null
                    ? o.getSemester().getSeason() + " " + o.getSemester().getYear()
                    : "—";

            System.out.println(localization.format(
                    LocalizationKey.OFFERING_SCHEDULE_HEADER,
                    courseName, teacher.getUsername(), semester));

            List<Lesson> lessons = o.getLessons();
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
