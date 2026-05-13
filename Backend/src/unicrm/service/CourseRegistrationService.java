package unicrm.service;
import unicrm.domain.Course;
import unicrm.domain.Manager;
import unicrm.domain.Student;
import unicrm.repository.CourseRepository;
import unicrm.repository.UserRepository;
public class CourseRegistrationService {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    public CourseRegistrationService(UserRepository userRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }
    public void registerStudent(Student student, Course course) {
        student.getRegisteredCourses().add(course);
        course.getEnrolledStudents().add(student);
        userRepository.save(student);
        courseRepository.save(course);
    }
    public void approveRegistration(Manager manager, Student student, Course course) {
        System.out.println(
                "Manager " + manager.getUsername()
                        + " approved course " + course.getName()
                        + " for student " + student.getUsername()
        );
    }
}
