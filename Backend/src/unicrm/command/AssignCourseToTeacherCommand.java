package unicrm.command;
import unicrm.domain.Course;
import unicrm.domain.Manager;
import unicrm.domain.Teacher;
import unicrm.domain.User;
import unicrm.repository.CourseRepository;
import unicrm.repository.UserRepository;
import unicrm.service.ManagerService;
import unicrm.session.UserSession;
import java.util.List;
public class AssignCourseToTeacherCommand {
    private final ManagerService managerService;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final UserSession userSession;
    public AssignCourseToTeacherCommand(
            ManagerService managerService,
            UserRepository userRepository,
            CourseRepository courseRepository,
            UserSession userSession
    ) {
        this.managerService = managerService;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.userSession = userSession;
    }
    public void execute() {
        User currentUser = userSession.getCurrentUser();
        if (currentUser instanceof Manager) {
            List<Course> courses = courseRepository.findAll();
            List<User> teachers = userRepository.findByRole("Teacher");
            if (!courses.isEmpty() && !teachers.isEmpty()) {
                managerService.assignCourseToTeacher(courses.getFirst(), (Teacher) teachers.getFirst());
            }
        }
    }
}
