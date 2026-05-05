package kz.edu.unicrm.command;
import kz.edu.unicrm.domain.Course;
import kz.edu.unicrm.domain.Student;
import kz.edu.unicrm.domain.User;
import kz.edu.unicrm.repository.CourseRepository;
import kz.edu.unicrm.service.CourseRegistrationService;
import kz.edu.unicrm.session.UserSession;
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
