package unicrm.command;

import unicrm.domain.CourseOffering;
import unicrm.domain.Teacher;
import unicrm.domain.User;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.repository.CourseOfferingRepository;
import unicrm.session.UserSession;

import java.util.List;

public class ViewTeacherCoursesCommand {

    private final CourseOfferingRepository offeringRepository;
    private final UserSession userSession;
    private final LocalizationService localization = LocalizationService.getInstance();

    public ViewTeacherCoursesCommand(CourseOfferingRepository offeringRepository, UserSession userSession) {
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
            System.out.println(localization.get(LocalizationKey.NO_COURSES_ASSIGNED));
            return;
        }

        for (int i = 0; i < myOfferings.size(); i++) {
            CourseOffering o = myOfferings.get(i);
            System.out.println((i + 1) + ". " + localization.format(
                    LocalizationKey.TEACHER_COURSE_ITEM,
                    o.getCourse().getName(),
                    o.getCourse().getCourseId(),
                    o.getSemester().getSeason(),
                    o.getSemester().getYear(),
                    o.getCapacity()
            ));
        }
    }
}
