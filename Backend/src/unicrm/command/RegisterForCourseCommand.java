package unicrm.command;
import unicrm.domain.Course;
import unicrm.domain.Student;
import unicrm.domain.User;
import unicrm.repository.CourseRepository;
import unicrm.service.CourseRegistrationService;
import unicrm.session.UserSession;
import java.util.List;
public class RegisterForCourseCommand {
    private final CourseRegistrationService courseRegistrationService;
    private final CourseRepository courseRepository;
    private final UserSession userSession;
    public RegisterForCourseCommand(
            CourseRegistrationService courseRegistrationService,
            CourseRepository courseRepository,
            UserSession userSession
    ) {
        this.courseRegistrationService = courseRegistrationService;
        this.courseRepository = courseRepository;
        this.userSession = userSession;
    }
    public void execute() {
        User currentUser = userSession.getCurrentUser();
        if (currentUser instanceof Student student) {
            List<Course> courses = courseRepository.findAll();
            if (!courses.isEmpty()) {
                courseRegistrationService.registerStudent(student, courses.getFirst());
            }
        }
    }
}
