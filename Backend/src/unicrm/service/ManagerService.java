package unicrm.service;
import unicrm.domain.Course;
import unicrm.domain.Teacher;
import unicrm.repository.CourseRepository;
import unicrm.repository.UserRepository;
import java.util.List;
public class ManagerService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    public ManagerService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }
    public void assignCourseToTeacher(Course course, Teacher teacher) {
        course.getAssignedTeachers().add(teacher);
        teacher.getAssignedCourses().add(course);
        courseRepository.save(course);
        userRepository.save(teacher);
    }
    public String generateAcademicReport() {
        List<Course> courses = courseRepository.findAll();
        return "System has " + courses.size() + " registered courses.";
    }
}
