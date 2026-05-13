package unicrm.command;
import unicrm.domain.Course;
import unicrm.domain.MarkData;
import unicrm.domain.Student;
import unicrm.domain.Teacher;
import unicrm.domain.User;
import unicrm.repository.UserRepository;
import unicrm.service.AcademicService;
import unicrm.session.UserSession;
import java.util.List;
public class PutMarkCommand {
    private final AcademicService academicService;
    private final UserRepository userRepository;
    private final UserSession userSession;
    public PutMarkCommand(
            AcademicService academicService,
            UserRepository userRepository,
            UserSession userSession
    ) {
        this.academicService = academicService;
        this.userRepository = userRepository;
        this.userSession = userSession;
    }
    public void execute() {
        User currentUser = userSession.getCurrentUser();
        if (currentUser instanceof Teacher teacher) {
            List<User> students = userRepository.findByRole("Student");
            if (!students.isEmpty() && !teacher.getAssignedCourses().isEmpty()) {
                Course course = teacher.getAssignedCourses().getFirst();
                Student student = (Student) students.getFirst();
                MarkData markData = new MarkData(30.0, 30.0, 40.0);
                academicService.putMark(teacher, student, course, markData);
            }
        }
    }
}
