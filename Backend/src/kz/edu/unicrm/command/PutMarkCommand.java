package kz.edu.unicrm.command;
import kz.edu.unicrm.domain.Course;
import kz.edu.unicrm.domain.MarkData;
import kz.edu.unicrm.domain.Student;
import kz.edu.unicrm.domain.Teacher;
import kz.edu.unicrm.domain.User;
import kz.edu.unicrm.repository.UserRepository;
import kz.edu.unicrm.service.AcademicService;
import kz.edu.unicrm.session.UserSession;
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
