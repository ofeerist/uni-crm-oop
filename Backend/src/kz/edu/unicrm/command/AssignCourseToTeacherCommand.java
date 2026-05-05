package kz.edu.unicrm.command;
import kz.edu.unicrm.domain.Course;
import kz.edu.unicrm.domain.Manager;
import kz.edu.unicrm.domain.Teacher;
import kz.edu.unicrm.domain.User;
import kz.edu.unicrm.repository.CourseRepository;
import kz.edu.unicrm.repository.UserRepository;
import kz.edu.unicrm.service.ManagerService;
import kz.edu.unicrm.session.UserSession;
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
